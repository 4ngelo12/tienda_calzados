package com.example.tienda_calzados.model.sales;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegisterSale(
        @NotNull
        LocalDate purchase_date,
        @NotNull
        @Positive
        BigDecimal total,
        @NotNull
        @Positive
        Long idProduct,
        @NotNull
        @Positive
        Long idCustomer
) {
}
