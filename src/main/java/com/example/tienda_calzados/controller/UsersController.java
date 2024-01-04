package com.example.tienda_calzados.controller;

import com.example.tienda_calzados.model.users.Users;
import com.example.tienda_calzados.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
@EnableMethodSecurity(securedEnabled = true)
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Employee", description = "Funcionalidades para el empleado")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> obtenerDatos() {
        return userService.obtenerDatos();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteEmp(@PathVariable Long id) {
        return userService.deleteUser(id);
    }


}
