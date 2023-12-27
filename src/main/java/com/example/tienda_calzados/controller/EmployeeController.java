package com.example.tienda_calzados.controller;

import com.example.tienda_calzados.model.users.employee.Employees;
import com.example.tienda_calzados.service.EmployeeService;
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
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employees>> obtenerDatos() {
        return employeeService.obtenerDatos();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteEmp(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }
}
