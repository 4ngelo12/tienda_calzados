package com.example.tienda_calzados.service;

import com.example.tienda_calzados.model.products.Products;
import com.example.tienda_calzados.model.shoppingcart.ListShoppingCartData;
import com.example.tienda_calzados.model.shoppingcart.RegisterShoppingCart;
import com.example.tienda_calzados.model.shoppingcart.ResponseShoppingCartRegister;
import com.example.tienda_calzados.model.shoppingcart.Shoppingcart;
import com.example.tienda_calzados.model.users.Users;
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
        Users customer = usersRepository.getReferenceById(data.userId());
        Products product = productRepository.getReferenceById(data.productId());

        // Validar si el product ya esta en el carrito
        if (existsByUsersIdAndProductsId(data.userId(), data.productId())) {
            var shoppingcartData = shoppingCartRepository.findByUsersIdAndProductsId(data.userId(), data.productId());
            // Actualización de la cantidad de productos
            Shoppingcart shoppingcart = shoppingCartRepository.save(new Shoppingcart(updateProductQuantity(data.userId(),
                    data.productId(), data.amount()), customer, product));
            deleteElement(shoppingcartData.getId());

            return new ResponseShoppingCartRegister(shoppingcart);
        }

        Shoppingcart shoppingcart = shoppingCartRepository.save(new Shoppingcart(data.amount(), customer, product));

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

    public List<ListShoppingCartData> getShoppingCartByUserId(Long id) {
        return shoppingCartRepository.findByUsersId(id).stream().map(ListShoppingCartData::new).toList();
    }

    public List<Shoppingcart> getAllShoppingCartbyUserId(Long id) {
        return shoppingCartRepository.findByUsersId(id);
    }

    public Boolean existsByUsersIdAndProductsId(Long userId, Long productId) {
        return shoppingCartRepository.existsByUsersIdAndProductsId(userId, productId);
    }

    public Integer updateProductQuantity(Long userId, Long productId, Integer quantity) {
        Shoppingcart shoppingcart = shoppingCartRepository.findByUsersIdAndProductsId(userId, productId);
        shoppingcart.setAmount(shoppingcart.getAmount() + quantity);
        return shoppingcart.getAmount();
    }
}
