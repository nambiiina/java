package com.example.catalogservice;

import com.example.catalogservice.entities.Category;
import com.example.catalogservice.entities.Product;
import com.example.catalogservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

@SpringBootApplication
public class CatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ProductRepository productRepository) {
        return args -> {
            Category informatics = new Category("informatics", "high tech");
            Category clothes = new Category("clothes", "mode design");
            productRepository.save(new Product(null, "mouse", 25.6, informatics));
            productRepository.save(new Product(null, "monitor", 126.3, informatics));
            productRepository.save(new Product(null, "keyboard", 150.0, informatics));
            productRepository.save(new Product(null, "speaker", 250.1, informatics));
            productRepository.save(new Product(null, "shorts", 15.2, clothes));
            productRepository.save(new Product(null, "t-shirt", 7.9, clothes));
            productRepository.save(new Product(null, "pants", 19.5, clothes));
            productRepository.save(new Product(null, "shirt", 6.7, clothes));
        };
    }

}
