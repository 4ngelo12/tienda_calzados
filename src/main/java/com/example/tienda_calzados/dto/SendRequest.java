package com.example.tienda_calzados.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record SendRequest(
        @NotBlank
        @Email
        String to,
        @NotBlank
        String subject,
        @Positive
        @NotNull
        Integer template,
        @NotNull
        List<MetaData> metaData
) {
}
