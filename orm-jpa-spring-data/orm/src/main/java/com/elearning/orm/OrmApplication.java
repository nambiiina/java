package com.elearning.orm;

import com.elearning.orm.entities.Product;
import com.elearning.orm.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class OrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrmApplication.class, args);
	}

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder().name("Laptop").price(BigDecimal.valueOf(999.99)).quantity(3).build());
            productRepository.save(Product.builder().name("Keyboard").price(BigDecimal.valueOf(65.99)).quantity(10).build());
            productRepository.save(Product.builder().name("Mouse").price(new BigDecimal("25.01")).quantity(20).build());

            List<Product> products = productRepository.findAll();
            products.forEach(System.out::println);
            System.out.println("############################## findByNameContainsIgnoreCase ##############################");
            List<Product> searchProducts = productRepository.findByNameContainsIgnoreCase("P");
            searchProducts.forEach(System.out::println);
            System.out.println("############################## findByNameContainsAndPriceGreaterThan ##############################");
            searchProducts = productRepository.search("o", new BigDecimal("65.99"));
            searchProducts.forEach(System.out::println);
        };
    }

}
