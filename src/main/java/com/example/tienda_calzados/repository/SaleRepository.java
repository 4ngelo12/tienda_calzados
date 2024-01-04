package com.example.tienda_calzados.repository;

import com.example.tienda_calzados.model.sales.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
