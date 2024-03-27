package com.example.tienda_calzados.model.users;

import jakarta.validation.constraints.NotBlank;

public record ResponseResetPassword(
        @NotBlank
        String message
) {
}
