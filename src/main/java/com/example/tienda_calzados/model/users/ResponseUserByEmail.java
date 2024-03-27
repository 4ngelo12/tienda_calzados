package com.example.tienda_calzados.model.users;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ResponseUserByEmail(
        @Positive
        @NotNull
        Long id
) {
}
