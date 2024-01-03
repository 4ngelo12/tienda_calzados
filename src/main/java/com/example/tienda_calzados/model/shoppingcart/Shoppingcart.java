package com.example.tienda_calzados.model.shoppingcart;

import com.example.tienda_calzados.model.products.Products;
import com.example.tienda_calzados.model.users.customer.Customers;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@Table(name = "shoppingcart")
@Entity(name = "ShoppingCart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Shoppingcart {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(nullable = false)
    private Integer amount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customers customers;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Products products;

    public Shoppingcart(RegisterShoppingCart shoppingCart, Customers customers, Products products) {
        this.amount = shoppingCart.amount();
        this.customers = customers;
        this.products = products;
    }
}
