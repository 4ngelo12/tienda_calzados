package com.example.tienda_calzados.service;

import com.example.tienda_calzados.model.shoppingcart.RegisterShoppingCart;
import com.example.tienda_calzados.model.shoppingcart.ResponseShoppingCartRegister;
import com.example.tienda_calzados.model.shoppingcart.Shoppingcart;
import com.example.tienda_calzados.model.validation.RegisterValidation;
import com.example.tienda_calzados.repository.ProductRepository;
import com.example.tienda_calzados.repository.ShoppingCartRepository;
import com.example.tienda_calzados.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    List<RegisterValidation<RegisterShoppingCart>> validadores;

    public ResponseShoppingCartRegister saveShoppingCart(RegisterShoppingCart data) {
        validadores.forEach(v -> v.validation(data));
        var customer = usersRepository.getReferenceById(data.userId());
        var product = productRepository.getReferenceById(data.productId());
        Shoppingcart shoppingcart = shoppingCartRepository.save(new Shoppingcart(data, customer, product));

        return new ResponseShoppingCartRegister(shoppingcart);
    }
}
