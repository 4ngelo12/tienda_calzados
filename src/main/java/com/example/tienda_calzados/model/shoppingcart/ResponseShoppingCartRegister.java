package com.example.tienda_calzados.model.shoppingcart;

import java.math.BigDecimal;

public record ResponseShoppingCartRegister(String code, Integer amount, BigDecimal subTotal,
                                           Long userId, Long productId) {
    public ResponseShoppingCartRegister(Shoppingcart data) {
        this(data.getCode(), data.getAmount(), data.getSubTotal(),
                data.getUsers().getId(), data.getProducts().getId());
    }
}
