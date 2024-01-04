package com.example.tienda_calzados.model.details;

import com.example.tienda_calzados.model.products.Products;
import com.example.tienda_calzados.model.sales.Sale;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@Table(name = "details")
@Entity(name = "Detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,###.##")
    private BigDecimal subTotal;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Products products;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "purchase_id")
    private Sale sale;
}