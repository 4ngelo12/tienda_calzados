package com.example.tienda_calzados.model.shoppingcart;

import java.math.BigDecimal;

public record ResponseShoppingCartRegister(Integer amount, Long customerId, Long productId) {
    public ResponseShoppingCartRegister(Shoppingcart data) {
        this(data.getAmount(), data.getCustomers().getId(), data.getProducts().getId());
    }
}
