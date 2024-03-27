package com.example.tienda_calzados.model.templates;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record RegisterTemplate(
        @NotNull
        MultipartFile file,
        @NotBlank
        String name,
        @NotBlank
        String description,
        List<String> vars) {
}
