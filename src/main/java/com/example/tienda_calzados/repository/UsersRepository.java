package com.example.tienda_calzados.repository;

import com.example.tienda_calzados.model.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    UserDetails findByEmail(String email);
    @Query("""
            select e from User e
            where e.email=:email
            """)
    Users getUserData(String email);
    Boolean existsByIdAndActiveTrue(Long id);
}
