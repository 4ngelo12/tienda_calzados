package com.example.tienda_calzados.repository;

import com.example.tienda_calzados.model.details.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailsRepository  extends JpaRepository<Detail, Long> {
    List<Detail> findBySaleId(Long SaleId);
}
