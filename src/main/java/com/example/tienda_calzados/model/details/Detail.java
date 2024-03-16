package com.example.tienda_calzados.model.details;

import com.example.tienda_calzados.model.products.Products;
import com.example.tienda_calzados.model.sales.Sale;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"description", "purchase_price", "sale_price", "stock", "active"})
    @JoinColumn(name = "product_id")
    private Products products;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "purchase_id")
    @JsonIgnoreProperties({"purchase_date", "total", "users"})
    private Sale sale;

    public Detail(RegisterDetail data, Products products, Sale sale) {
        this.quantity = data.quantity();;
        this.subTotal = data.subTotal();
        this.products = products;
        this.sale = sale;
    }
}
