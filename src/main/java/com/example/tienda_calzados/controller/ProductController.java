package com.example.tienda_calzados.controller;

import com.example.tienda_calzados.dto.Body;
import com.example.tienda_calzados.model.products.*;
import com.example.tienda_calzados.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

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
    public ResponseEntity<?> registerProduct(@RequestParam String name, @RequestParam String description,
                                             @RequestParam MultipartFile file, @RequestParam Integer size,
                                             @RequestParam String brand, @RequestParam BigDecimal purchase_price,
                                             @RequestParam BigDecimal sale_price, @RequestParam Integer stock,
                                             @RequestParam Long categoryId) {
        String contentType = file.getContentType();
        String image = file.getOriginalFilename();
        if (contentType == null) {
            return ResponseEntity.badRequest().build();
        }
        if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
            return ResponseEntity.badRequest().body(
                    new Body("El formato de la imagen no es valido, solo se aceptan imagenes jpeg o png"));
        }
        var data = new RegisterProduct(name, description, image, size, brand,
                purchase_price, sale_price, stock, categoryId);
        var response = productService.saveProduct(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductRegister> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProducts(id));
    }

    @GetMapping
    public ResponseEntity<Page<ListProductData>> getAllActiveProducts(
            Pageable paginacion) {
        return ResponseEntity.ok(productService.getAllActiveProducts(paginacion));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ListProductData>> getAllProducts(Pageable paginacion) {
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
