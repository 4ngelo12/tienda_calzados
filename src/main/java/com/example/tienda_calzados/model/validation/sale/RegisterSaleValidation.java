package com.example.tienda_calzados.model.validation.sale;

import com.example.tienda_calzados.model.sales.RegisterSale;
import com.example.tienda_calzados.model.validation.RegisterValidation;
import com.example.tienda_calzados.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterSaleValidation implements RegisterValidation<RegisterSale> {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public void validation(RegisterSale data) {
        Boolean exists = usersRepository.existsByIdAndActiveTrue(data.userId());

    }
}
