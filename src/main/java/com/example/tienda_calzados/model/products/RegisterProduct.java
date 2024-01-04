package com.example.tienda_calzados.model.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Set;

public record RegisterProduct(
        @NotBlank
        String name,
        @NotBlank
        String description,
        String image,
        @NotNull
        @Positive
        Integer size,
        @NotBlank
        String brand,
        @NotNull
        @Positive
        BigDecimal purchase_price,
        @NotNull
        @Positive
        BigDecimal sale_price,
        @NotNull
        @Positive
        Integer stock,
        @NotNull
        Long categoryId
) {
}
