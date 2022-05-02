package com.example.billingservice.service.feign;

import com.example.billingservice.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductService {
    @GetMapping("/products/{id}")
    Product findProductById(@PathVariable(name = "id") Long id);
    @GetMapping("/products")
    PagedModel<Product> findAll(); //PageModel<> Hateoas
}
