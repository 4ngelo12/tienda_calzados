package com.example.tienda_calzados.model.users;

import java.time.LocalDate;

public record ResponseUserUpdate(Long id, String name, String lastName, String email, LocalDate birthdate) {
    public ResponseUserUpdate(Users users) {
        this(users.getId(), users.getName(), users.getLastname(), users.getEmail(), users.getBirthdate());
    }
}
