package org.sid.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF : Not generate synchronizer token and place it in the session
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
        // Disable frames
        http.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer
                .frameOptions(frameOptionsConfig -> frameOptionsConfig
                        .disable()));
        // Permit all incoming requests.
        http.authorizeHttpRequests((authz) -> authz
                .anyRequest().permitAll());
        return http.build();
    }

}
