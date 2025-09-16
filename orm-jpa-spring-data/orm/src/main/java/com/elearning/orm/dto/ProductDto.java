package com.elearning.orm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "ProductDto", description = "Product expose by API")
public record ProductDto(
        @Schema(description = "Identifiant technique", accessMode = Schema.AccessMode.READ_ONLY)
        Long id,

        @NotBlank
        @Size(max = 120)
        @Schema(description = "Nom du produit", example = "iPhone 15")
        String name,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        @Digits(integer = 8, fraction = 2)
        BigDecimal price,

        @NotNull
        @PositiveOrZero
        Integer quantity
) {
}
