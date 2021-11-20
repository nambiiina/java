package com.sid.mcsbank;

import com.sid.mcsbank.entities.Account;
import com.sid.mcsbank.entities.AccountType;
import com.sid.mcsbank.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class McsBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(McsBankApplication.class, args);
    }

    @Bean
    CommandLineRunner start (AccountRepository accountRepository) {
        return args -> {
            accountRepository.save(new Account(null, Math.random()*9000, new Date(), AccountType.CURRENT));
            accountRepository.save(new Account(null, Math.random()*9000, new Date(), AccountType.SAVINGS));
            accountRepository.save(new Account(null, Math.random()*9000, new Date(), AccountType.CURRENT));
            for (Account account : accountRepository.findAll()) {
                System.out.println(account);
            }
        };
    }

}
