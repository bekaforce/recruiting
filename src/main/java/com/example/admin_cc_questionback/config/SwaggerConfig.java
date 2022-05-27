package com.example.admin_cc_questionback.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info()
                .title("VCV Swagger API")
                .version("1.0.0"));
    }
}
