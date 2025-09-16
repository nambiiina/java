package com.elearning.orm.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Entity
@Getter // Pas de Setter général => Pour garantir l'invariant métier, expose des méthodes métier : updatePrice
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    public void udpatePrice(BigDecimal newPrice) {
        Assert.isTrue(newPrice.signum() >= 0, "Price must be positive");
        this.price = newPrice;
    }
}
