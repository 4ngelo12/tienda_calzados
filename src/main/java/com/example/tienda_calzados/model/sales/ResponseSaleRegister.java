package com.example.tienda_calzados.model.sales;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ResponseSaleRegister(Long id, String code, LocalDate purchase_date, BigDecimal total,
                                   Long customerId) {
    public ResponseSaleRegister(Sale sale) {
        this(sale.getId(), sale.getCode(), sale.getPurchase_date(), sale.getTotal(),
                sale.getCustomers().getId());
    }
}
