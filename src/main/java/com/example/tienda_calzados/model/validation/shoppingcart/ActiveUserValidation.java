package com.example.tienda_calzados.model.validation.shoppingcart;

import com.example.tienda_calzados.model.shoppingcart.RegisterShoppingCart;
import com.example.tienda_calzados.model.validation.RegisterValidation;
import com.example.tienda_calzados.repository.UsersRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiveUserValidation implements RegisterValidation<RegisterShoppingCart> {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public void validation(RegisterShoppingCart data) {
        var active = usersRepository.existsByIdAndActiveTrue(data.userId());

        if (!active) {
            throw new ValidationException("El usuario no esta activo");
        }
    }
}
