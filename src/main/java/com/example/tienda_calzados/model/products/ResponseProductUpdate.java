package com.example.tienda_calzados.model.products;

import com.example.tienda_calzados.model.category.Category;

import java.math.BigDecimal;

public record ResponseProductUpdate(Long id, String name, String description, String image, Integer size, String brand,
                                    BigDecimal purchase_price, BigDecimal sale_price, Integer stock, Category categoryId) {
    public ResponseProductUpdate(Products products) {
        this(products.getId(), products.getName(), products.getDescription(), products.getImage(), products.getSize(),
                products.getBrand(), products.getPurchase_price(), products.getSale_price(), products.getStock(),
                products.getCategory());
    }
}
