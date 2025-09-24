package com.example.web_jee_spring_mvc;

import com.example.web_jee_spring_mvc.entities.Product;
import com.example.web_jee_spring_mvc.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class WebJeeSpringMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebJeeSpringMvcApplication.class, args);
	}

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                            .name("Laptop")
                            .price(new BigDecimal("999"))
                            .quantity(10)
                    .build());
            productRepository.save(Product.builder()
                    .name("Printer")
                    .price(new BigDecimal("450"))
                    .quantity(3)
                    .build());
            productRepository.save(Product.builder()
                    .name("Mouse")
                    .price(new BigDecimal("35"))
                    .quantity(10)
                    .build());
            productRepository.save(Product.builder()
                    .name("SmartPhone")
                    .price(new BigDecimal("500"))
                    .quantity(10)
                    .build());
            productRepository.findAll().forEach(System.out::println);
        };
    }

}
