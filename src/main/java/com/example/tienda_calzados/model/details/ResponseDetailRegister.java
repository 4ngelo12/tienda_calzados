package com.example.tienda_calzados.model.details;

import java.math.BigDecimal;

public record ResponseDetailRegister(Integer quantity, BigDecimal subTotal, Long productId, Long saleId) {
    public ResponseDetailRegister(Detail detail) {
        this(detail.getQuantity(), detail.getSubTotal(), detail.getProducts().getId(), detail.getSale().getId());
    }
}
