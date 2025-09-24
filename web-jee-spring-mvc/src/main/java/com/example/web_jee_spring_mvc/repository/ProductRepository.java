package com.example.web_jee_spring_mvc.repository;

import com.example.web_jee_spring_mvc.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
