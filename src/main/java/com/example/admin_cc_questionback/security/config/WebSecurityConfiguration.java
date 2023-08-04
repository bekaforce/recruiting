package com.example.admin_cc_questionback.security.config;
import com.example.admin_cc_questionback.security.filter.JwtTokenAuthenticationFilter;
import com.example.admin_cc_questionback.security.filter.RestAccessDeniedHandler;
import com.example.admin_cc_questionback.security.filter.SecurityAuthenticationEntryPoint;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.SpringSecurityLdapTemplate;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@PropertySource("classpath:application.yml")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${ldap.provider.url}")
    private String providerUrl;

    @Value("${ldap.provider.userdn}")
    private String providerUserDn;

    @Value("${ldap.provider.password}")
    private String providerPassword;

    @Value("${ldap.user.dn.patterns.bishkek}")
    private String userDnPatternsBishkek;

    @Value("${ldap.user.dn.patterns.all}")
    private String userDnPatternsAll;

    public WebSecurityConfiguration() {
        super(true);
    }

    @Bean
    LdapAuthoritiesPopulator ldapAuthoritiesPopulator() {

        class MyLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {

            private final String[] GROUP_ATTRIBUTE = {"cn"};
            private final String REQUIRED_GROUP = "APP_Recruiting";
            private final SpringSecurityLdapTemplate ldapTemplate;

            private MyLdapAuthoritiesPopulator(ContextSource contextSource) {
                ldapTemplate = new SpringSecurityLdapTemplate(contextSource);
            }

            @Override
            public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {

                String GROUP_MEMBER_OF = "memberof";
                String[] groupDns = userData.getStringAttributes(GROUP_MEMBER_OF);
                String[] filteredDns = Stream.of(groupDns).filter(groupDn -> groupDn.contains(REQUIRED_GROUP)).toArray(String[]::new);

                if (filteredDns.length < 1) {
                    throw new AuthenticationServiceException("You are not authorized to login");
                }

                String roles = Stream.of(filteredDns).map(groupDn -> {
                    LdapName groupLdapName = (LdapName) ldapTemplate.retrieveEntry(groupDn, GROUP_ATTRIBUTE).getDn();
                    return groupLdapName.getRdns().stream().map(Rdn::getValue).reduce((a, b) -> b).orElse(null);
                }).map(x -> (String) x).collect(Collectors.joining(","));

                return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
            }
        }

        return new MyLdapAuthoritiesPopulator(contextSource());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userSearchFilter("CN={0}")
                .userSearchBase(userDnPatternsAll)
                .groupSearchBase(userDnPatternsAll)
                .ldapAuthoritiesPopulator(ldapAuthoritiesPopulator())
                .contextSource(contextSource());
    }

    @Bean
    public BaseLdapPathContextSource contextSource() {
        DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(providerUrl);
        contextSource.setUserDn(providerUserDn);
        contextSource.setPassword(providerPassword);
        return contextSource;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        String secret = IOUtils.toString(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("secret.key")), Charset.defaultCharset());

        String[] AUTH_WHITELIST = {
                "/admin/auth/**",
                // -- Swagger UI v2
//                "/v2/api-docs",
//                "/swagger-resources",
//                "/swagger-resources/**",
                //"/configuration/ui",
                //"/configuration/security",
//                "/swagger-ui.html",
                //"/webjars/**",
                // -- Swagger UI v3 (OpenAPI)
//              "/v3/api-docs/**",
//              "/swagger-ui/**",
                // other public endpoints of your API may be appended to this array
//                "/profile",
//              "/admin/api/candidate/get/{id}",
                //"/admin/api/hooligan/**",
                "/admin/api/video/download/**",
                "/admin/api/video/stream/**"
        };

        httpSecurity
                .csrf().disable()
                .cors().disable()
                .addFilterAfter(jwtTokenAuthenticationFilter("/admin/api/**", secret), ExceptionTranslationFilter.class)
                .addFilterAfter(corsFilter(), ExceptionTranslationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new SecurityAuthenticationEntryPoint())
                .accessDeniedHandler(new RestAccessDeniedHandler())
                .and()
                .anonymous()
                .and()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                /* Разрешения для анонимусоов, чтобы могли авторизоваться */
                //.antMatchers("/hello").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()// Swagger resource endpoint
                /* Все остальные запросы требуют авторизации посредством аннотации @PreAuthorize("hasAnyRole('ROLE_имя_группы_AD')") */
                .anyRequest().authenticated(); //enable auth
//        .anyRequest().permitAll(); //disable auth
    }

    private JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter(String path, String secret) {
        return new JwtTokenAuthenticationFilter(path, secret);
    }

    private CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.addAllowedOrigin("*");
        config.addAllowedHeader(ORIGIN);
        config.addAllowedHeader(CONTENT_TYPE);
        config.addAllowedHeader(ACCEPT);
        config.addAllowedHeader(AUTHORIZATION);
        config.addAllowedMethod(GET);
        config.addAllowedMethod(PUT);
        config.addAllowedMethod(POST);
        config.addAllowedMethod(OPTIONS);
        config.addAllowedMethod(DELETE);
        config.addAllowedMethod(PATCH);
        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

