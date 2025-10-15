package com.example.web_jee_spring_mvc_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl(DataSource dataSource) {
        JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        jdbcTokenRepositoryImpl.setDataSource(dataSource);
//        jdbcTokenRepositoryImpl.setCreateTableOnStartup(true); // Create persistent_logins if missing
        return jdbcTokenRepositoryImpl;
    }

    /*
     * JdbcUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService
     * UserDetailsManager extends UserDetailsService
     * */
    @Bean
    public UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder, DataSource dataSource) {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("####"))
                .roles("USER","ADMIN")
                .build();
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        if (!jdbcUserDetailsManager.userExists(user.getUsername())) {
            jdbcUserDetailsManager.createUser(user);
        }
        if (!jdbcUserDetailsManager.userExists(admin.getUsername())) {
            jdbcUserDetailsManager.createUser(admin);
        }
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, LogoutHandler logoutHandler, JdbcTokenRepositoryImpl jdbcTokenRepository) throws Exception {
        return httpSecurity
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/login").defaultSuccessUrl("/", true).permitAll())
                .rememberMe(httpSecurityRememberMeConfigurer ->
                        httpSecurityRememberMeConfigurer
                                .tokenRepository(jdbcTokenRepository)
                                .tokenValiditySeconds(7*24*3600))
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutUrl("/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessUrl("/login"))
//                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//                        authorizationManagerRequestMatcherRegistry.requestMatchers("/patients/**").hasRole("USER"))
//                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//                        authorizationManagerRequestMatcherRegistry.requestMatchers("/patients","/patients/new", "/patients/delete").hasRole("ADMIN"))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry.requestMatchers("/webjars/**").permitAll())
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/403"))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry.anyRequest().authenticated())
                .build();
    }

    @Bean
    public LogoutHandler rememberMeCleanup(JdbcTokenRepositoryImpl tokenRepository) {
        return (request, response, authentication) -> {
            if (authentication != null && authentication.isAuthenticated()) tokenRepository.removeUserTokens(authentication.getName());
        };
    }

}
