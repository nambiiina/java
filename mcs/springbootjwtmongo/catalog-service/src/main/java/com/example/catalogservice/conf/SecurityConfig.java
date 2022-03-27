package com.example.catalogservice.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Redefine spring security configuration
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http); // Enable/Disable authentication
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Deactivate session
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/products/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/products").hasAnyAuthority("ADMIN", "USER");
        http.authorizeHttpRequests().anyRequest().authenticated();
        /**
         * 1- Intercept request using filter
         * 2- Read http request that contains jwt
         * 3- sign the token with the same secret that was used to generate the token
         * 4- load user context and get user role
         * 5- load user in context
         */
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
