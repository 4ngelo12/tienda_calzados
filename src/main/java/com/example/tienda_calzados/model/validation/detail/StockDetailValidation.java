package com.example.tienda_calzados.model.validation.detail;

import com.example.tienda_calzados.model.details.RegisterDetail;
import com.example.tienda_calzados.model.validation.RegisterValidation;
import com.example.tienda_calzados.repository.ProductRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockDetailValidation implements RegisterValidation<RegisterDetail> {
    @Autowired
    ProductRepository productRepository;

    @Override
    public void validation(RegisterDetail data) {
        var disponible = productRepository.existsByIdAndStockGreaterThan(data.productId(), data.quantity());

        if (!disponible) {
            throw new ValidationException("No hay suficiente stock");
        }
    }
}
