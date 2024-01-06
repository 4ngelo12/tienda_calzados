package com.example.tienda_calzados.controller;

import com.example.tienda_calzados.infra.security.JWTToken;
import com.example.tienda_calzados.infra.security.TokenService;
import com.example.tienda_calzados.model.users.AuthUserData;
import com.example.tienda_calzados.model.users.Users;
import com.example.tienda_calzados.model.users.RegisterUser;
import com.example.tienda_calzados.model.users.ResponseUserRegister;
import com.example.tienda_calzados.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@Tag(name = "Autenticacion", description = "obtiene el token para el usuario asignado que da " +
        "acceso al resto de endpoint")
public class UserController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    @Operation(
            summary = "Registra empleados en la aplicación",
            description = "",
            tags = {"post"})
    public ResponseEntity<ResponseUserRegister> registerEmployee(@RequestBody @Valid RegisterUser data) {
        var response = usersService.saveUser(data);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    @Operation(
            summary = "inicia sesión en la aplicación",
    description = "",
    tags = {"post"})
    public ResponseEntity<JWTToken> authEmployee(@RequestBody @Valid AuthUserData
                                                         authData) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(authData.username(),
                authData.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarTokenEmp((Users) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new JWTToken(JWTtoken));
    }
}
