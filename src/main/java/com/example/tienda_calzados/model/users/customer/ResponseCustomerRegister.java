package com.example.tienda_calzados.model.users.customer;

import java.time.LocalDate;

public record ResponseCustomerRegister(Long id, String name, String lastName, String email,
                                       LocalDate birthdate) {
    public ResponseCustomerRegister(Customers customers) {
        this(customers.getId(), customers.getName(), customers.getLastname(), customers.getUsername(),
                customers.getBirthdate());
    }
}
