package com.example.customerservice;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(new Customer(null, "Microsoft", "microsoft@mail.test"));
            customerRepository.save(new Customer(null, "Apple", "apple@mail.test"));
            customerRepository.save(new Customer(null, "Dell", "dell@mail.test"));
            customerRepository.save(new Customer(null, "Lenovo", "lenovo@mail.test"));
        };
    }

}
