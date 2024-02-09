package org.sid.springsecurity.config;

import lombok.AllArgsConstructor;
import org.sid.springsecurity.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class UserDetailsServiceConfig {

    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Custom user form login
//        httpSecurity.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
//                .loginPage("/login").permitAll());
        // Native user form login
        httpSecurity.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.defaultSuccessUrl("/"));
        // Remember me
        httpSecurity.rememberMe(Customizer.withDefaults());
        // Secure location
//        httpSecurity.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
//                .requestMatchers("/h2-console").permitAll()
//                .requestMatchers("/api/accounts/users/**").hasRole("USER")
//                .requestMatchers("/api/accounts/roles/**").hasRole("ADMIN"));
        // Redirect to not authorize page
        httpSecurity.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/notAuthorized"));
        // Need authentication
        httpSecurity.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                .anyRequest().authenticated());
        // Use UserDetailsService for authentication
        httpSecurity.userDetailsService(userDetailsServiceImpl);
        return httpSecurity.build();
    }

}
