package com.example.tienda_calzados.model.users.validation;

import com.example.tienda_calzados.model.users.customer.RegisterCustomer;
import com.example.tienda_calzados.repository.CustomerRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerActive implements CustomerValidation{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void validation(RegisterCustomer data) {
        var empActive = customerRepository.getUserData(data.email());

        if (empActive != null) {
            throw new ValidationException("El correo ingresado ya esta registrado");
        }
    }
}
