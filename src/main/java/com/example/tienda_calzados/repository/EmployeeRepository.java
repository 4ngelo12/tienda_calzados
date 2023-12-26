package com.example.tienda_calzados.repository;

import com.example.tienda_calzados.model.users.employee.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {
    UserDetails findByEmail(String email);
    @Query("""
            select e from Employee e
            where e.email=:email
            """)
    Employees getUserData(String email);
    @Query("""
        select e.active from Employee e
        where e.id=:idEmployee
    """)
    Boolean findActivoById(Long idEmployee);
}
