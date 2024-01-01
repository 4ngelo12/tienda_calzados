package com.example.tienda_calzados.controller;

import com.example.tienda_calzados.infra.security.JWTToken;
import com.example.tienda_calzados.infra.security.TokenService;
import com.example.tienda_calzados.model.users.customer.AuthCustomerData;
import com.example.tienda_calzados.model.users.customer.Customers;
import com.example.tienda_calzados.model.users.customer.RegisterCustomer;
import com.example.tienda_calzados.model.users.customer.ResponseCustomerRegister;
import com.example.tienda_calzados.model.users.employee.AuthEmployeeData;
import com.example.tienda_calzados.model.users.employee.Employees;
import com.example.tienda_calzados.model.users.employee.RegisterEmployee;
import com.example.tienda_calzados.model.users.employee.ResponseEmployeeRegister;
import com.example.tienda_calzados.service.CustomerService;
import com.example.tienda_calzados.service.EmployeeService;
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
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register/emp")
    @Operation(
            summary = "Registra empleados en la aplicación",
            description = "",
            tags = {"post"})
    public ResponseEntity<ResponseEmployeeRegister> registerEmployee(@RequestBody @Valid RegisterEmployee data) {
        var response = employeeService.saveEmployee(data);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/customer")
    @Operation(
            summary = "Registra clientes en la aplicación",
            description = "",
            tags = {"post"})
    public ResponseEntity<ResponseCustomerRegister> resgisterCustomer(@RequestBody @Valid RegisterCustomer data) {
        var response = customerService.saveCustomer(data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login/emp")
    @Operation(
            summary = "inicia sesión en la aplicación",
    description = "",
    tags = {"post"})
    public ResponseEntity<JWTToken> authEmployee(@RequestBody @Valid AuthEmployeeData
                                                         authData) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(authData.username(),
                authData.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarTokenEmp((Employees) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new JWTToken(JWTtoken));
    }

    @PostMapping("/login/customer")
    @Operation(
            summary = "inicia sesión en la aplicación",
            description = "",
            tags = {"post"})
    public ResponseEntity<Object> authCustomer(@RequestBody @Valid AuthCustomerData
                                                         authData) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(authData.username(),
                authData.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarTokenCustomer((Customers) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new JWTToken(JWTtoken));
    }
}
