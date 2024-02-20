package org.sid.customerservice;

import org.sid.customerservice.entity.Customer;
import org.sid.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
		return args -> {
			customerRepository.save(Customer.builder().name("Ninah").email("ninah@gmail.com").build());
			customerRepository.save(Customer.builder().name("Nambinina").email("nambinina@gmail.com").build());
			customerRepository.save(Customer.builder().name("Harti").email("harti@gmail.com").build());
		};
	}
}
