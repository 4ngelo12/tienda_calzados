package com.example.tienda_calzados.model.users.employee;

import java.time.LocalDate;

public record ResponseEmployeeRegister(Long id, String name, String lastName, String email,
                                       LocalDate birthdate) {
    public ResponseEmployeeRegister(Employees employees) {
        this(employees.getId(), employees.getName(), employees.getLastname(), employees.getUsername(),
                employees.getBirthdate());
    }
}
