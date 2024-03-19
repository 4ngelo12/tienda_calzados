package com.example.tienda_calzados.model.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserByEmail(
        @Email
        @NotBlank
        String email) {
}
