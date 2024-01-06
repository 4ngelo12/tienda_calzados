package com.example.tienda_calzados.controller;

import com.example.tienda_calzados.model.users.Users;
import com.example.tienda_calzados.service.UsersService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@EnableMethodSecurity(securedEnabled = true)
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Users", description = "Funcionalidades para el empleado")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<List<Users>> obtenerDatos() {
        return usersService.obtenerDatos();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteEmp(@PathVariable Long id) {
        return usersService.deleteUser(id);
    }


}
