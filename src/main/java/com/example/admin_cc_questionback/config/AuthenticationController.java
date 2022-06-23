package com.example.admin_cc_questionback.config;

import com.example.admin_cc_questionback.controller.Url;
import com.example.admin_cc_questionback.security.JwtUtils;
import com.example.admin_cc_questionback.security.model.AuthenticationRequest;
import com.example.admin_cc_questionback.security.model.AuthenticationResponse;
import com.nimbusds.jose.JOSEException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@CrossOrigin
@RequestMapping(value = "auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @RequestMapping(method = POST)
    public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest)
            throws AuthenticationException, IOException, JOSEException {

        String username = authenticationRequest.getUsername().toLowerCase();
        String password = authenticationRequest.getPassword();

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String secret = IOUtils.toString(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("secret.key")), Charset.defaultCharset());
        int expirationInMinutes = 8 * 60;

        String token = JwtUtils.generateHMACToken(username, authentication.getAuthorities(), secret, expirationInMinutes);

        // Return the token
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}

