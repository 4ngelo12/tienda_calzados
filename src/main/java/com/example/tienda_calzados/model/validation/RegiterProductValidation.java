package com.example.tienda_calzados.model.validation;

import com.example.tienda_calzados.model.products.RegisterProduct;
import com.example.tienda_calzados.repository.ProductRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegiterProductValidation implements RegisterValidation<RegisterProduct>{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void validation(RegisterProduct data) {
    }
}
