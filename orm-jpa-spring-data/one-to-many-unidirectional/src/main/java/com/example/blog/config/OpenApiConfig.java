package com.example.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blog API")
                        .description("Démo Spring Data JPA : mapping @OneToMany unidirectionnel "
                                + "avec @JoinColumn entre Post et Comment.")
                        .version("0.0.1-SNAPSHOT"));
    }
}
