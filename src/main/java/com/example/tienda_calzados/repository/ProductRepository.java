package com.example.tienda_calzados.repository;

import com.example.tienda_calzados.model.products.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    Boolean existsByCode(String code);
    Boolean existsByIdAndActiveTrue(Long id);
    @Query("""
    select count(p) > 0 from Product p
    where p.id = :id and p.stock >= :amount
    """)
    boolean existsByIdAndStockGreaterThan(@Param("id") Long id, @Param("amount") Integer amount);
    Page<Products> findAllByActiveTrue(Pageable pageable);
    @Query("""
        select p from Product p
    """)
    Page<Products> findAllProducts(Pageable pageable);
    List<Products> findAllByCategory_Id(Long categoryId);
}
