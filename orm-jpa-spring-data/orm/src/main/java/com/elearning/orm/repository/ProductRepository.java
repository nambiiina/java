package com.elearning.orm.repository;

import com.elearning.orm.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainsIgnoreCase(String keyWord);
    List<Product> findByNameContainsAndPriceGreaterThan(String keyWord, BigDecimal price);
    @Query("select p from Product p where p.name like:x and p.price>:y")
    List<Product> search(@Param("x") String keyWord, @Param("y") BigDecimal price);
}
