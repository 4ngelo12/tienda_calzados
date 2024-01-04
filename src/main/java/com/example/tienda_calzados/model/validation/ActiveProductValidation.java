package com.example.tienda_calzados.model.validation;

import com.example.tienda_calzados.model.shoppingcart.RegisterShoppingCart;
import com.example.tienda_calzados.repository.ProductRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiveProductValidation implements RegisterValidation<RegisterShoppingCart> {
    @Autowired
    ProductRepository productRepository;

    @Override
    public void validation(RegisterShoppingCart data) {
        Boolean active = productRepository.findByIdAndActiveIsTrue(data.productId());

        if (!active) {
            throw new ValidationException("El producto no esta activo");
        }
    }
}
