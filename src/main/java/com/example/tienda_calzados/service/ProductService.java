package com.example.tienda_calzados.service;

import com.example.tienda_calzados.model.products.Products;
import com.example.tienda_calzados.model.products.RegisterProduct;
import com.example.tienda_calzados.model.products.ResponseProductRegister;
import com.example.tienda_calzados.model.validation.RegisterValidation;
import com.example.tienda_calzados.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    List<RegisterValidation<RegisterProduct>> validadores;

    public ResponseProductRegister saveProduct(RegisterProduct data) {
        validadores.forEach(v -> v.validation(data));
        Integer count = Math.toIntExact(productRepository.count()) + 1;
        Products products = productRepository.save(new Products(data, count));

        return new ResponseProductRegister(products);
    }
}
