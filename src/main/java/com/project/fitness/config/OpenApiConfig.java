package com.project.fitness.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {

    @Bean
    public OpenAPI cuOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Fitness Tracking API")
                        .version("v1.0")
                        .description("production Grade api's")
                        .contact(new Contact()
                                .name("Takshey")
                                .url("https://Takeshy.com")
                                .email("takeshy@gmail.com")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://Takeshy.com")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"));
    }
}
