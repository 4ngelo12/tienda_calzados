package com.example.tienda_calzados.service;

import com.example.tienda_calzados.model.category.Category;
import com.example.tienda_calzados.model.products.*;
import com.example.tienda_calzados.repository.CategoryRepository;
import com.example.tienda_calzados.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public Page<ListProductData> getAllActiveProducts(Pageable paginacion) {
        return productRepository.findAllByActiveTrue(paginacion).map(ListProductData::new);
    }

    public Page<ListProductData> getAllProducts(Pageable paginacion) {
        return productRepository.findAllProducts(paginacion).map(ListProductData::new);
    }

    public ResponseProductRegister getProducts(Long id) {
        return new ResponseProductRegister(productRepository.getReferenceById(id));
    }

    @Transactional
    public ResponseEntity<?> activateProduct(Long id) {
        Products product = productRepository.getReferenceById(id);
        product.activateProduct();
        return ResponseEntity.noContent().build();
    }

    @Transactional
    public ResponseEntity<Object> deleteProduct(Long id) {
        Products product = productRepository.getReferenceById(id);
        product.deleteProduct();
        return ResponseEntity.noContent().build();
    }

    @Transactional
    public void reduceStock(Long id, Integer amount) {
        Products product = productRepository.getReferenceById(id);

        if (product.getStock() < amount) {
            throw new IllegalStateException("No hay suficiente stock");
        }

        product.reduceStock(amount);
    }
}
