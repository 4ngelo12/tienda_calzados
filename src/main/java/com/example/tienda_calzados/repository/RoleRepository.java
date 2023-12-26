package com.example.tienda_calzados.repository;

import com.example.tienda_calzados.model.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
