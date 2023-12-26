package com.example.tienda_calzados.repository;

import com.example.tienda_calzados.model.users.customer.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {
    UserDetails findByEmail(String email);
    @Query("""
            select c from Customer c
            where c.email=:email
            """)
    Customers getUserData(String email);

    @Query("""
        select c.active from Customer c
        where c.id=:idCustomer
    """)
    Boolean findActivoById(Long idCustomer);
}
