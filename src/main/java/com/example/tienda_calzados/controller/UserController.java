package com.example.tienda_calzados.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
/* @Tag(name = "Autenticacion", description = "obtiene el token para el usuario asignado que da " +
        "acceso al resto de endpoint")
 */
public class UserController {
    @GetMapping("/hello")
    public String HelloWorld() {
        return "Hello World";
    }
}
