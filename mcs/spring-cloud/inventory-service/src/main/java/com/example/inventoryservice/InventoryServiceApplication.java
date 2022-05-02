package com.example.inventoryservice;

import com.example.inventoryservice.entities.Product;
import com.example.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration) {
        return args -> {
            restConfiguration.exposeIdsFor(Product.class);
            productRepository.save(new Product(null, "Alienware x14", 2347.99));
            productRepository.save(new Product(null, "MacBook Air", 1129.00));
            productRepository.save(new Product(null, "Legion 5i 17 - Phantom Blue & Shadow Black", 1999.00));
            productRepository.save(new Product(null, "Surface Laptop 4", 799.99));
        };
    }

}
