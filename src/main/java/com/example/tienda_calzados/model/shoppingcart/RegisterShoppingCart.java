package com.example.tienda_calzados.model.shoppingcart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RegisterShoppingCart(
        @NotNull
        @Positive
        Integer amount,
        @NotNull
        @Positive
        Long customerId,
        @NotNull
        @Positive
        Long productId
) {
}
