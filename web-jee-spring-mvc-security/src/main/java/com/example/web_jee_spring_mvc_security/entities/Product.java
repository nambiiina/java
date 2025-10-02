package com.example.web_jee_spring_mvc_security.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor (access = AccessLevel.PROTECTED) // Authorize JPA or subclass to create object via réflexion / heritage.
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@Builder
@Getter // No general setter => To guarantee business invariance, expose business methods : updatePrice
@ToString
public class Product {
    @Id @GeneratedValue
    private Long id;

    @NotBlank(message = "Name is mandator")
    @Size(min = 2, max = 120, message = "Name must be between 2 and 120 characters")
    @Column(nullable = false, length = 120)
    private String name;

    @PositiveOrZero(message = "Price must be >= 0")
    @Digits(integer = 8, fraction = 2, message = "Price must have <= 8 integer digits and <= 2 decimals")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @PositiveOrZero(message = "Quantity must be >= 0")
    @Column(nullable = false)
    private Integer quantity;

    public void updatePrice(BigDecimal newPrice) {
        /*
        * Spring assert
        * To validate input data or technical data
        * */
        Assert.isTrue(newPrice.signum() >= 0, "Price must be greater than or equal to zero");
        this.price = newPrice;
    }
}
