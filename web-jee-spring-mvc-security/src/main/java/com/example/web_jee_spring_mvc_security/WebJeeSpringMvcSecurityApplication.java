package com.example.web_jee_spring_mvc_security;

import com.example.web_jee_spring_mvc_security.entities.Product;
import com.example.web_jee_spring_mvc_security.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class WebJeeSpringMvcSecurityApplication {

    public static void main(String[] args) {
		SpringApplication.run(WebJeeSpringMvcSecurityApplication.class, args);
	}

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                            .name("Computer")
                            .price(new BigDecimal("1500.99"))
                            .quantity(15)
                    .build());

            productRepository.save(Product.builder()
                    .name("Printer")
                    .price(new BigDecimal("250.99"))
                    .quantity(5)
                    .build());

            productRepository.save(Product.builder()
                    .name("Mouse")
                    .price(new BigDecimal("45.99"))
                    .quantity(50)
                    .build());

            productRepository.save(Product.builder()
                    .name("Keyboard")
                    .price(new BigDecimal("25.99"))
                    .quantity(30)
                    .build());
            productRepository.save(Product.builder()
                    .name("Screen")
                    .price(new BigDecimal("450"))
                    .quantity(25)
                    .build());


            productRepository.findAll().forEach(System.out::println);
        };
    }
}
