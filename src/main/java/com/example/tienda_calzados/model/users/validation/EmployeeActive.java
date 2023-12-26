package com.example.tienda_calzados.model.users.validation;

import com.example.tienda_calzados.model.users.employee.RegisterEmployee;
import com.example.tienda_calzados.repository.EmployeeRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeActive implements EmployeeValidation{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void validation(RegisterEmployee data) {
        var empActive = employeeRepository.getUserData(data.email());

        if (empActive != null) {
            throw new ValidationException("La cuenta no se encuentra activa");
        }
    }
}
