package com.example.tienda_calzados.repository;

import com.example.tienda_calzados.model.products.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    Boolean existsByCode(String code);
    Boolean getProductsByIdAndStockIsGreaterThan(Long id, Integer amount);
}
