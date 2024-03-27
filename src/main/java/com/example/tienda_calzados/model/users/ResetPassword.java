package com.example.tienda_calzados.model.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ResetPassword(
        @NotNull
        @Positive
        Long id,
        @NotBlank
        String password
) {
}
