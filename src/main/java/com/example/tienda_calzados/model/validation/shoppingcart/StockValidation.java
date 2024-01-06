package com.example.tienda_calzados.model.validation.shoppingcart;

import com.example.tienda_calzados.model.shoppingcart.RegisterShoppingCart;
import com.example.tienda_calzados.model.validation.RegisterValidation;
import com.example.tienda_calzados.repository.ProductRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockValidation implements RegisterValidation<RegisterShoppingCart> {
    @Autowired
    ProductRepository productRepository;

    @Override
    public void validation(RegisterShoppingCart data) {
        var disponible = productRepository.existsByIdAndStockGreaterThan(data.productId(), data.amount());

        if (!disponible) {
            throw new ValidationException("No hay suficiente stock");
        }
    }
}
