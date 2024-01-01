package com.example.tienda_calzados.model.validation;

import com.example.tienda_calzados.model.users.employee.RegisterEmployee;
import com.example.tienda_calzados.repository.EmployeeRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterEmployeeValidation implements RegisterValidation<RegisterEmployee> {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void validation(RegisterEmployee data) {
        var emailExiste = employeeRepository.getUserData(data.email());

        if (emailExiste != null) {
            throw new ValidationException("El correo ingresado ya esta registrado");
        }
    }
}
