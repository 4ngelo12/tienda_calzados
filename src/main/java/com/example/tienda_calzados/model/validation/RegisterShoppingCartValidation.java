package com.example.tienda_calzados.model.validation;

import com.example.tienda_calzados.model.shoppingcart.RegisterShoppingCart;
import com.example.tienda_calzados.repository.ProductRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterShoppingCartValidation implements RegisterValidation<RegisterShoppingCart>{
    @Autowired
    ProductRepository productRepository;

    @Override
    public void validation(RegisterShoppingCart data) {
        var disponible = productRepository.getProductsByIdAndStockIsGreaterThan(data.productId(), data.amount());

        if (!disponible) {
            throw new ValidationException("No se encuentra disponible");
        }
    }
}
