package com.example.tienda_calzados.model.shoppingcart;

import com.example.tienda_calzados.model.products.Products;
import com.example.tienda_calzados.model.users.Users;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "shoppingcart")
@Entity(name = "ShoppingCart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Shoppingcart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 16, nullable = false)
    private String code;
    @Column(nullable = false)
    private Integer amount;
    @Column(nullable = false)
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,###.##")
    private BigDecimal subTotal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Users users;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Products products;

    public Shoppingcart(RegisterShoppingCart shoppingCart, Users users, Products products) {
        this.code = UUID.randomUUID().toString().substring(0, 16);
        this.amount = shoppingCart.amount();
        this.subTotal = products.subTotal(this.amount, products.getSale_price());
        this.users = users;
        this.products = products;
    }
}
