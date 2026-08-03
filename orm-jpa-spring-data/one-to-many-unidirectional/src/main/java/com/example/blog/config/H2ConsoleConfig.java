package com.example.blog.config;

import jakarta.servlet.Servlet;
import org.h2.server.web.JakartaWebServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "spring.h2.console", name = "enabled", havingValue = "true")
public class H2ConsoleConfig {

    @Value("${spring.h2.console.path:/h2-console}")
    private String path;

    @Bean
    public ServletRegistrationBean<Servlet> h2ConsoleServlet() {
        return new ServletRegistrationBean<>(new JakartaWebServlet(), path + "/*");
    }
}
