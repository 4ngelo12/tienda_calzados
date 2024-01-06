package com.example.tienda_calzados.repository;

import com.example.tienda_calzados.model.sales.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    Page<Sale> findByUsersId(Pageable pageable, Long UsersId);
}
