package com.example.tienda_calzados.service;

import com.example.tienda_calzados.infra.security.TokenService;
import com.example.tienda_calzados.model.users.customer.Customers;
import com.example.tienda_calzados.model.users.customer.RegisterCustomer;
import com.example.tienda_calzados.model.users.customer.ResponseCustomerRegister;
import com.example.tienda_calzados.model.users.validation.CustomerValidation;
import com.example.tienda_calzados.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    List<CustomerValidation> validadores;

    public ResponseCustomerRegister saveEmployee(RegisterCustomer data) {
        validadores.forEach(v -> v.validation(data));
        Customers customer = customerRepository.save(new Customers(data, passwordEncoder));

        return new ResponseCustomerRegister(customer);
    }
}
