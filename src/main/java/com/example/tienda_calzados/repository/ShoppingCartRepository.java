package com.example.tienda_calzados.repository;

import com.example.tienda_calzados.model.shoppingcart.Shoppingcart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<Shoppingcart, Long> {
    Page<Shoppingcart> findAll(Pageable pageable);
    List<Shoppingcart> findByUsersId(Long usersId);
    void deleteByUsersId(Long usersId);
    Long countByUsersId(Long usersId);
}
