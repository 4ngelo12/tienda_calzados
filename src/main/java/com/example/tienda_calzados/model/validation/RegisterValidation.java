package com.example.tienda_calzados.model.validation;

import com.example.tienda_calzados.model.users.customer.RegisterCustomer;

public interface RegisterValidation<T> {
    public void validation(T data);
}
