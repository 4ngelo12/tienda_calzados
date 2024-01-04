package com.example.tienda_calzados.repository;

import com.example.tienda_calzados.model.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    UserDetails findByEmail(String email);
    @Query("""
            select e from User e
            where e.email=:email
            """)
    Users getUserData(String email);
    @Query("""
        select e.active from User e
        where e.id=:idEmployee
    """)
    Boolean findActivoById(Long idEmployee);
    Boolean findByIdAndActiveIsTrue(Long id);
}
