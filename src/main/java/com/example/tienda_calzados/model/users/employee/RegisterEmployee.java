package com.example.tienda_calzados.model.users.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record RegisterEmployee(
        @NotBlank
        @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚüÜñÑ ]{1,45}+$", message = "El nombre ingresado no es valido")
        String name,
        @NotBlank
        @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚüÜñÑ ]{1,45}+$", message = "El apellido ingresado no es valido ")
        String lastname,
        @NotBlank
        @Email
        String email,
        @NotNull
        LocalDate birthdate,
        @NotBlank
        String password,
        @NotNull
        Long idRole
        ) {
}
