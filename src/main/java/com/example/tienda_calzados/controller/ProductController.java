package com.example.tienda_calzados.controller;

import com.example.tienda_calzados.model.products.*;
import com.example.tienda_calzados.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
@EnableMethodSecurity(securedEnabled = true)
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Product", description = "Funcionalidades para los productos")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ResponseProductRegister> registerProduct(@RequestBody @Valid RegisterProduct data) {
        var response = productService.saveProduct(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductRegister> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProducts(id));
    }

    @GetMapping
    public ResponseEntity<Page<ListProductData>> getAllProducts(
            Pageable paginacion) {
        return ResponseEntity.ok(productService.getAllProducts(paginacion));
    }

    @PatchMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @Transactional
    public ResponseEntity<ResponseProductUpdate> editProduct(@RequestBody @Valid UpdateProduct data) {
        var response = productService.updateProduct(data);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/activate/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity activateProduct(@PathVariable Long id) {
        return productService.activateProduct(id);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
