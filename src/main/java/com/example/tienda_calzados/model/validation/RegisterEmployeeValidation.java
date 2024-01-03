package com.example.tienda_calzados.model.validation;

import com.example.tienda_calzados.model.users.RegisterUser;
import com.example.tienda_calzados.repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterEmployeeValidation implements RegisterValidation<RegisterUser> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void validation(RegisterUser data) {
        var emailExiste = userRepository.getUserData(data.email());

        if (emailExiste != null) {
            throw new ValidationException("El correo ingresado ya esta registrado");
        }
    }
}
