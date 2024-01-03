package com.example.tienda_calzados.model.products;

import java.math.BigDecimal;

public record ResponseProductRegister(Long id, String code, String name, String description, String image, Integer size,
                                      String brand, BigDecimal purchase_price, BigDecimal sale_price, Integer stock,
                                      Long categoryId) {
    public ResponseProductRegister(Products products) {
        this(products.getId(), products.getCode(), products.getName(), products.getDescription(), products.getImage(),
                products.getSize(), products.getBrand(), products.getPurchase_price(), products.getSale_price(),
                products.getStock(), products.getCategory().getId());
    }
}
