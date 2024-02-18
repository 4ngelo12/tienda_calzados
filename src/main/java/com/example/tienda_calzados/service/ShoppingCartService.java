package com.example.tienda_calzados.service;

import com.example.tienda_calzados.model.shoppingcart.ListShoppingCartData;
import com.example.tienda_calzados.model.shoppingcart.RegisterShoppingCart;
import com.example.tienda_calzados.model.shoppingcart.ResponseShoppingCartRegister;
import com.example.tienda_calzados.model.shoppingcart.Shoppingcart;
import com.example.tienda_calzados.model.validation.RegisterValidation;
import com.example.tienda_calzados.repository.ProductRepository;
import com.example.tienda_calzados.repository.ShoppingCartRepository;
import com.example.tienda_calzados.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UsersRepository usersRepository;
    private final ProductRepository productRepository;
    List<RegisterValidation<RegisterShoppingCart>> validadores;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, UsersRepository usersRepository,
                               ProductRepository productRepository,
                               List<RegisterValidation<RegisterShoppingCart>> validadores) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.usersRepository = usersRepository;
        this.productRepository = productRepository;
        this.validadores = validadores;
    }

    public ResponseShoppingCartRegister saveShoppingCart(RegisterShoppingCart data) {
        validadores.forEach(v -> v.validation(data));
        var customer = usersRepository.getReferenceById(data.userId());
        var product = productRepository.getReferenceById(data.productId());
        Shoppingcart shoppingcart = shoppingCartRepository.save(new Shoppingcart(data, customer, product));

        return new ResponseShoppingCartRegister(shoppingcart);
    }

    public ResponseEntity<Object> deleteElement(Long id) {
        shoppingCartRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public void deleteAllElement(Long id) {
        shoppingCartRepository.deleteByUsersId(id);
    }

    public Page<ListShoppingCartData> getAllShoppingCartView(Pageable pageable) {
        return shoppingCartRepository.findAll(pageable).map(ListShoppingCartData::new);
    }

    public List<Shoppingcart> getAllShoppingCartbyUserId(Long id) {
        return shoppingCartRepository.findByUsersId(id);
    }
}
