package com.example.tienda_calzados.model.details;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record RegisterDetail(
        @NotNull
        @Positive
        Integer quantity,
        @NotNull
        @Positive
        BigDecimal subTotal,
        @NotNull
        @Positive
        Long productId,
        @NotNull
        @Positive
        Long saleId) {
}
