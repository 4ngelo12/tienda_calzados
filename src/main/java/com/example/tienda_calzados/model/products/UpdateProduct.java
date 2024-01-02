package com.example.tienda_calzados.model.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateProduct(
        @NotNull
        @Positive
        Long id,
        String name,
        String description,
        String image,
        @Positive
        Integer size,
        String brand,
        @Positive
        BigDecimal purchase_price,
        @Positive
        BigDecimal sale_price,
        @Positive
        Integer stock,
        Long Idcategory
) {
}
