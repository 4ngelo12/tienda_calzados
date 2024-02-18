package com.example.tienda_calzados.service;

import com.example.tienda_calzados.model.details.Detail;
import com.example.tienda_calzados.model.details.RegisterDetail;
import com.example.tienda_calzados.model.details.ResponseDetailRegister;
import com.example.tienda_calzados.model.validation.RegisterValidation;
import com.example.tienda_calzados.repository.DetailsRepository;
import com.example.tienda_calzados.repository.ProductRepository;
import com.example.tienda_calzados.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class DetailService {
    @Autowired
    private DetailsRepository detailsRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    List<RegisterValidation<RegisterDetail>> validadores;

    public ResponseDetailRegister saveDetail(RegisterDetail data) {
        validadores.forEach(v -> v.validation(data));
        var product = productRepository.getReferenceById(data.productId());
        var sale = saleRepository.getReferenceById(data.saleId());
        Detail detail = detailsRepository.save(new Detail(data, product, sale));

        return new ResponseDetailRegister(detail);
    }

    public void insertData(Long userId, Long saleId) {
        var shoppingcart = shoppingCartService.getAllShoppingCartbyUserId(userId);
        shoppingcart.forEach(element -> {
            RegisterDetail detail = new RegisterDetail(element.getAmount(),
                    element.getSubTotal(), element.getProducts().getId(), saleId);
            saveDetail(detail);
        });

        shoppingCartService.deleteAllElement(userId);
    }
}
