package com.example.web_jee_spring_mvc_security.repository;

import com.example.web_jee_spring_mvc_security.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
