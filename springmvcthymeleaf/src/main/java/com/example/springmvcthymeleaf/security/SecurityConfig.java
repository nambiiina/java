package com.example.springmvcthymeleaf.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth); // by default configure one "user" with generated password
        PasswordEncoder passwordEncoder = passwordEncoder();
//        auth.inMemoryAuthentication().withUser("user0").password("{noop}1234").roles("USER"); // => noop disable encoded password
        System.out.println("#####################################");
        System.out.println(passwordEncoder.encode("1234"));
        System.out.println("#####################################");
        auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder.encode("1234")).roles("USER");
        auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder.encode("1234")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http); // => Enable security
        http.formLogin(); // => use form authentication by spring security
//        http.httpBasic(); // => spring security ask to browser the request needs authentication
        http.authorizeHttpRequests().antMatchers("/save**/**", "/delete**/**", "/add**/**", "/edit**/**").hasRole("ADMIN");
        http.authorizeHttpRequests().antMatchers("/patients**/**").hasRole("USER");
//        http.authorizeHttpRequests().anyRequest().authenticated();
        http.csrf(); // => enable by default
        http.exceptionHandling().accessDeniedPage("/notAuthorized");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
