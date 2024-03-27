package com.example.tienda_calzados.controller;

import com.example.tienda_calzados.model.category.Category;
import com.example.tienda_calzados.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
@EnableMethodSecurity(securedEnabled = true)
@SecurityRequirement(name = "bearer-key")
@Secured("ROLE_ADMIN")
@Tag(name = "Category", description = "Funcionalidades para categorias del producto")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getCategory() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
