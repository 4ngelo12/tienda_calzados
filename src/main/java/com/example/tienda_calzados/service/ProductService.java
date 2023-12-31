package com.example.tienda_calzados.service;

import com.example.tienda_calzados.model.category.Category;
import com.example.tienda_calzados.model.products.*;
import com.example.tienda_calzados.model.validation.RegisterValidation;
import com.example.tienda_calzados.repository.CategoryRepository;
import com.example.tienda_calzados.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseProductRegister saveProduct(RegisterProduct data) {
        Integer position = Products.getCount();
        if (position == null) {
            position = Math.toIntExact(productRepository.count()) + 1;
        }
        Category category = categoryRepository.findById(data.categoryId()).get();
        Products products = productRepository.save(new Products(data, position, category));
        products.setNumber(position);

        return new ResponseProductRegister(products);
    }

    public ResponseProductUpdate updateProduct(UpdateProduct data) {
        Category category = categoryRepository.findById(data.Idcategory()).get();
        Products products = productRepository.getReferenceById(data.id());
        products.updateProduct(data, category);

        return new ResponseProductUpdate(products);
    }

    public ResponseEntity activateProduct(Long id) {
        var product = productRepository.getReferenceById(id);
        product.activateProduct();
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> deleteProduct(Long id) {
        var product = productRepository.getReferenceById(id);
        product.deleteProduct();
        return ResponseEntity.noContent().build();
    }
}
