package com.example.tienda_calzados.model.users;

import java.time.LocalDate;

public record ResponseUserRegister(Long id, String name, String lastName, String email,
                                   LocalDate birthdate) {
    public ResponseUserRegister(Users employees) {
        this(employees.getId(), employees.getName(), employees.getLastname(), employees.getUsername(),
                employees.getBirthdate());
    }
}
