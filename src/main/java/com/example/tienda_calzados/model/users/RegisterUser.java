package com.example.tienda_calzados.model.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record RegisterUser(
        @NotBlank(message = "")
        @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚüÜñÑ ]{3,45}+$", message = "El nombre ingresado no es valido")
        String name,
        @NotBlank(message = "No se permite el campo vacio")
        @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚüÜñÑ ]{3,45}+$", message = "El apellido ingresado no es valido ")
        String lastname,
        @NotBlank
        @Email
        String email,
        LocalDate birthdate,
        @NotBlank
        String password,
        @NotNull
        Long idRole
        ) {
}
