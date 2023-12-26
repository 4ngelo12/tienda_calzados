package com.example.tienda_calzados.service;

import com.example.tienda_calzados.infra.errors.IntegrityValidation;
import com.example.tienda_calzados.infra.security.TokenService;
import com.example.tienda_calzados.model.users.employee.Employees;
import com.example.tienda_calzados.model.users.employee.RegisterEmployee;
import com.example.tienda_calzados.model.users.employee.ResponseEmployeeRegister;
import com.example.tienda_calzados.model.users.validation.EmployeeValidation;
import com.example.tienda_calzados.repository.EmployeeRepository;
import com.example.tienda_calzados.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    List<EmployeeValidation> validadores;

    public ResponseEmployeeRegister saveEmployee(RegisterEmployee data) {
        if (roleRepository.findById(data.idRole()).isEmpty()) {
            throw new IntegrityValidation("El rol ingresado no existe");
        }

        validadores.forEach(v -> v.validation(data));
        var role = roleRepository.findById(data.idRole()).get();
        Employees emp = employeeRepository.save(new Employees(data, role, passwordEncoder));

        return new ResponseEmployeeRegister(emp);
    }
}
