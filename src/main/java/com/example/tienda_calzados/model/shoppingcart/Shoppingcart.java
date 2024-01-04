package com.example.tienda_calzados.model.shoppingcart;

import com.example.tienda_calzados.model.products.Products;
import com.example.tienda_calzados.model.users.Users;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    @JoinColumn(name = "user_id")
    private Users users;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Products products;

    public Shoppingcart(RegisterShoppingCart shoppingCart, Users users, Products products) {
        this.amount = shoppingCart.amount();
        this.users = users;
        this.products = products;
    }
}
