package org.sid.springsecurity.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class InMemoryAuthentication {

    private PasswordEncoder passwordEncoder;

    /**
     * Create in memmory user fot authentication
     * @return InMemoryUserDetailsManager
     */
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(
                user,
                User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
//                User.withUsername("user2").password("{noop}1234").roles("USER").build(), // Do not encode password
                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build()
        );
    }

    /**
     * Create filter.
     * @param httpSecurity
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // Use form login
        httpSecurity.formLogin(Customizer.withDefaults());
        // Need authentication
        httpSecurity.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                .anyRequest().authenticated());
        return httpSecurity.build();
    }
}
