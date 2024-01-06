package com.example.tienda_calzados.model.validation.sale;

import com.example.tienda_calzados.model.sales.RegisterSale;
import com.example.tienda_calzados.model.validation.RegisterValidation;
import com.example.tienda_calzados.repository.UsersRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiveUserSaleValidation implements RegisterValidation<RegisterSale> {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public void validation(RegisterSale data) {
        var active = usersRepository.existsByIdAndActiveTrue(data.userId());

        if (!active) {
            throw new ValidationException("El usuario no esta activo");
        }
    }
}
