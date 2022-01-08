package com.sid.mcsbank;

import com.sid.mcsbank.entities.Account;
import com.sid.mcsbank.entities.AccountType;
import com.sid.mcsbank.entities.Client;
import com.sid.mcsbank.entities.Log;
import com.sid.mcsbank.repositories.AccountRepository;
import com.sid.mcsbank.repositories.ClientRepository;
import com.sid.mcsbank.repositories.LogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
public class McsBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(McsBankApplication.class, args);
    }

    @Bean
    CommandLineRunner start (AccountRepository accountRepository, RepositoryRestConfiguration restConfiguration, ClientRepository clientRepository, LogRepository logRepository) {
        return args -> {
            Client c1 = clientRepository.save(new Client(null, "Thierry", null));
            Client c2 = clientRepository.save(new Client(null, "Nambinina", null));
            //Expose id
            restConfiguration.exposeIdsFor(Account.class);
            accountRepository.save(new Account(null, Math.random()*9000, new Date(), AccountType.CURRENT, c1));
            accountRepository.save(new Account(null, Math.random()*9000, new Date(), AccountType.SAVINGS, c1));
            accountRepository.save(new Account(null, Math.random()*9000, new Date(), AccountType.CURRENT, c2));
            Log log = logRepository.save(new Log(null, "this is a simple message"));
            for (Account account : accountRepository.findAll()) {
                System.out.println(account.getBalance());
            }
        };
    }

}
