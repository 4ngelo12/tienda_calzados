package com.example.tienda_calzados.model.shoppingcart;

import java.math.BigDecimal;

public record ResponseShoppingCartRegister(Integer amount, Long userId, Long productId) {
    public ResponseShoppingCartRegister(Shoppingcart data) {
        this(data.getAmount(), data.getUsers().getId(), data.getProducts().getId());
    }
}
