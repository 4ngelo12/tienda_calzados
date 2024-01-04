package com.example.tienda_calzados.repository;

import com.example.tienda_calzados.model.products.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    Boolean existsByCode(String code);
    Boolean findByIdAndActiveIsTrue(Long id);
    @Query("""
    select count(p) > 0 from Product p
    where p.id = :id and p.stock > :amount
    """)
    Boolean existsByIdAndStockGreaterThan(@Param("id") Long id, @Param("amount") Integer amount);
}
