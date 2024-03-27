package com.example.tienda_calzados.model.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.Set;

public record RegisterProduct(
        @NotBlank
        @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚüÜñÑ ]{3,45}+$", message = "El nombre ingresado no es valido")
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
        @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,###.##")
        @Positive
        BigDecimal purchase_price,
        @NotNull
        @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,###.##")
        @Positive
        BigDecimal sale_price,
        @NotNull
        @Positive
        Integer stock,
        @NotNull
        Long categoryId
) {
}
