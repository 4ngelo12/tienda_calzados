package com.example.tienda_calzados.model.users;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record UpdateUser(
        @NotNull
        @Positive
        Long id,
        String name,
        String lastname,
        String email,
        LocalDate birthdate
) {
}
