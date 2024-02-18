package com.example.tienda_calzados.model.users;

import com.example.tienda_calzados.model.Role.Role;

import java.time.LocalDate;

public record ListUserData(Long id, String name, String lastname, String email, LocalDate birthdate, Role role) {
    public ListUserData(Users data) {
        this(data.getId(), data.getName(), data.getLastname(), data.getEmail(), data.getBirthdate(), data.getRole());
    }
}
