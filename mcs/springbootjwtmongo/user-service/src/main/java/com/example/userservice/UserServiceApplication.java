package com.example.userservice;

import com.example.userservice.entities.AppRole;
import com.example.userservice.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class UserServiceApplication {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountService accountService) {
        return args -> {
            accountService.saveRole(new AppRole(null, "USER", null));
            accountService.saveRole(new AppRole(null, "ADMIN", null));
            Stream.of("user1", "user2", "user3", "admin").forEach(username -> {
                accountService.saveUser(username, "1234", "1234");
            });
            accountService.addRoleToUser("admin", "ADMIN");
        };
    }

}
