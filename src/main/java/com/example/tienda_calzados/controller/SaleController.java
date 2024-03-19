package com.example.tienda_calzados.controller;

import com.example.tienda_calzados.model.sales.RegisterSale;
import com.example.tienda_calzados.model.sales.ResponseSaleRegister;
import com.example.tienda_calzados.model.sales.Sale;
import com.example.tienda_calzados.model.users.Users;
import com.example.tienda_calzados.service.DetailService;
import com.example.tienda_calzados.service.SaleService;
import com.example.tienda_calzados.service.UsersService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
@CrossOrigin("*")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Sale", description = "Funcionalidades para las compras")
public class SaleController {
    @Autowired
    private SaleService saleService;
    @Autowired
    private DetailService detailService;
    @Autowired
    private UsersService usersService;

    @PostMapping
    public ResponseEntity<ResponseSaleRegister> saveSale(@RequestBody @Valid RegisterSale data) {
        var response = saleService.saveSale(data);
        Users user = usersService.getUser(response.userId());
        detailService.insertData(user.getId(), response.id());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<Sale>> getSalesByUserId(Pageable paginacion, @PathVariable Long id) {
        return ResponseEntity.ok(saleService.getSalesByUserId(paginacion, id));
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Object> getSale(@PathVariable Long id) {
        return ResponseEntity.ok(detailService.getDetailsBySaleId(id));
    }
}
