package com.example.tienda_calzados.controller;

import com.example.tienda_calzados.model.shoppingcart.RegisterShoppingCart;
import com.example.tienda_calzados.model.shoppingcart.ResponseShoppingCartRegister;
import com.example.tienda_calzados.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingcart")
@CrossOrigin("*")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "ShoppingCart", description = "Funcionalidades para el carrito")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping
    public ResponseEntity<ResponseShoppingCartRegister> registerShoppingCart(
            @RequestBody @Valid RegisterShoppingCart data) {
        var response = shoppingCartService.saveShoppingCart(data);
        return ResponseEntity.ok(response);
    }
}
