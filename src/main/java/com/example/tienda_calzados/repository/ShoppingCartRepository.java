package com.example.tienda_calzados.repository;

import com.example.tienda_calzados.model.shoppingcart.Shoppingcart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<Shoppingcart, String> {
}
