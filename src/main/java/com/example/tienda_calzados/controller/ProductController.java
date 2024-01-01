package com.example.tienda_calzados.controller;

import com.example.tienda_calzados.model.products.RegisterProduct;
import com.example.tienda_calzados.model.products.ResponseProductRegister;
import com.example.tienda_calzados.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@EnableMethodSecurity(securedEnabled = true)
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Product", description = "Funcionalidades para los productos")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseProductRegister> registerProduct(@RequestBody @Valid RegisterProduct data) {
        var response = productService.saveProduct(data);
        return ResponseEntity.ok(response);
    }
}
