package com.example.tienda_calzados.service;

import com.example.tienda_calzados.model.sales.RegisterSale;
import com.example.tienda_calzados.model.sales.ResponseSaleRegister;
import com.example.tienda_calzados.model.sales.Sale;
import com.example.tienda_calzados.model.validation.RegisterValidation;
import com.example.tienda_calzados.repository.SaleRepository;
import com.example.tienda_calzados.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    List<RegisterValidation<RegisterSale>> validadores;

    public ResponseSaleRegister saveSale(RegisterSale data) {
        validadores.forEach(v -> v.validation(data));
        var user = usersRepository.getReferenceById(data.userId());
        Sale sale = saleRepository.save(new Sale(data, user));

        return new ResponseSaleRegister(sale);
    }

    public Sale getSale(Long id) {
        return saleRepository.getReferenceById(id);
    }
}
