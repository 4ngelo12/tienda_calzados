package com.example.tienda_calzados.model.users.validation;

import com.example.tienda_calzados.model.users.customer.RegisterCustomer;
import com.example.tienda_calzados.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterCustomerValidation implements CustomerValidation{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void validation(RegisterCustomer data) {

    }
}
