package com.example.tienda_calzados.model.products;

import com.example.tienda_calzados.model.category.Category;

import java.math.BigDecimal;

public record ListProductData(Long id, Boolean active, String code, String name, String description, String image, Integer size,
                              String brand, BigDecimal purchase_price, BigDecimal sale_price, Integer stock,
                              Category category) {
    public ListProductData(Products data) {
        this(data.getId(), data.getActive(), data.getCode(), data.getName(), data.getDescription(), data.getImage(), data.getSize(),
                data.getBrand(), data.getPurchase_price(), data.getSale_price(), data.getStock(), data.getCategory());
    }
}
