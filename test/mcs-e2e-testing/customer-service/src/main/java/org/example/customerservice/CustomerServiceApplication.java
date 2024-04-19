package org.example.customerservice;

import lombok.extern.slf4j.Slf4j;
import org.example.customerservice.entities.Customer;
import org.example.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Slf4j
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	@Profile("!test")
	CommandLineRunner setup(CustomerRepository customerRepository) {
		return args -> {
			customerRepository.save(Customer.builder()
							.firstName("Randrianandrianina")
							.lastName("Thierry")
							.email("rthierry@mail.com")
					.build());
			customerRepository.save(Customer.builder()
							.firstName("Randrianandrianina")
							.lastName("Nambinina")
							.email("rnambinina@mail.com")
					.build());
			customerRepository.save(Customer.builder()
							.firstName("Randrianandrianina")
							.lastName("Harti")
							.email("rharti@mail.com")
					.build());
		};
	}

}
