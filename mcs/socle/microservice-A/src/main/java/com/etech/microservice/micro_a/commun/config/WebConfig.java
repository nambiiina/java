package com.etech.microservice.micro_a.commun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private Environment environment;
    
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
      registry.addMapping("/**")
              .allowedOrigins(environment.getProperty("cors.allowed.origins", String[].class))
              .allowedMethods("*");
    }

}