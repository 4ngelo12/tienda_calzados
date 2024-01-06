package com.example.tienda_calzados.repository;

import com.example.tienda_calzados.model.details.Detail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsRepository  extends JpaRepository<Detail, Long> {
    Page<Detail> findBySaleId(Pageable pageable, Long SaleId);
}
