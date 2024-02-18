package com.example.tienda_calzados.model.shoppingcart;

import com.example.tienda_calzados.model.products.Products;
import com.example.tienda_calzados.model.users.Users;

import java.math.BigDecimal;

public record ListShoppingCartData(Long id, String code, Integer amount, BigDecimal subTotal, Users users,
                                   Products products) {
    public ListShoppingCartData(Shoppingcart data) {
        this(data.getId(), data.getCode(), data.getAmount(), data.getSubTotal(), data.getUsers(), data.getProducts());
    }
}
