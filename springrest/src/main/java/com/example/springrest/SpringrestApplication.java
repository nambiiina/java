package com.example.springrest;

import com.example.springrest.entities.Account;
import com.example.springrest.entities.AccountType;
import com.example.springrest.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SpringrestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringrestApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountRepository accountRepository) {
        return args -> {
            accountRepository.save(new Account(null, 9800, new Date(), AccountType.CURRENT));
            accountRepository.save(new Account(null, 3500, new Date(), AccountType.SAVINGS));
            accountRepository.save(new Account(null, 12500, new Date(), AccountType.CURRENT));
            accountRepository.findAll().forEach(account -> {
                System.out.println(account.getType());
            });
        };
    }
}
