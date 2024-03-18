package com.example.tienda_calzados.controller;

import com.example.tienda_calzados.model.users.*;
import com.example.tienda_calzados.service.UsersService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@EnableMethodSecurity(securedEnabled = true)
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Users", description = "Funcionalidades para el empleado")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteEmp(@PathVariable Long id) {
        return usersService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> activateUser(@PathVariable Long id) {
        return usersService.activateUser(id);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseUserUpdate> updateUser(@RequestBody @Valid UpdateUser data) {
        return ResponseEntity.ok(usersService.userUpdate(data));
    }

    @GetMapping
    public ResponseUserRegister getUser(@RequestHeader("Authorization") String token) {
        var response = usersService.getUserData(token);
        return new ResponseUserRegister(response);
    }
}
