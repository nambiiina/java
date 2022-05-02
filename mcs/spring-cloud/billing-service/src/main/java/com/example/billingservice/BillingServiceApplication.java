package com.example.billingservice;

import com.example.billingservice.entities.Billing;
import com.example.billingservice.entities.Customer;
import com.example.billingservice.entities.Product;
import com.example.billingservice.entities.ProductItem;
import com.example.billingservice.repository.BillingRepository;
import com.example.billingservice.repository.ProductItemRepository;
import com.example.billingservice.service.feign.CustomerService;
import com.example.billingservice.service.feign.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillingRepository billingRepository, ProductItemRepository productItemRepository, CustomerService customerService, ProductService productService) {
        return args -> {
            Customer c1 = customerService.findCustomerById(1L);
            System.out.println("*****************");
            System.out.println(c1);
            System.out.println("*****************");
            Billing b1 = billingRepository.save(new Billing(null, new Date(), c1.getId(), null, null));

            PagedModel<Product> products = productService.findAll();
            products.getContent().forEach(product -> {
                productItemRepository.save(new ProductItem(null, product.getId(), null, product.getPrice(), 15, b1));
            });

//            Product p1 = productService.findProductById(1L);
//            System.out.println("*****************");
//            System.out.println(p1);
//            System.out.println("*****************");
//            productItemRepository.save(new ProductItem(null, p1.getId(), p1.getPrice(), 30, b1));
//            Product p2 = productService.findProductById(2L);
//            productItemRepository.save(new ProductItem(null, p2.getId(), p2.getPrice(), 45, b1));
//            Product p3 = productService.findProductById(3L);
//            productItemRepository.save(new ProductItem(null, p3.getId(), p3.getPrice(), 10, b1));
        };
    }
}
