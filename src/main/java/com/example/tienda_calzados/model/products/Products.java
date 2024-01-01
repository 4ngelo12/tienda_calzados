package com.example.tienda_calzados.model.products;

import com.example.tienda_calzados.model.category.Category;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10, nullable = false)
    private String code;
    @Column(length = 60, nullable = false)
    private String name;
    @Column(columnDefinition = "text", nullable = false)
    private String description;
    @Column(nullable = true)
    private String image;
    @Column(nullable = false)
    private Integer size;
    @Column(length = 20, nullable = false)
    private String brand;
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,###.##")
    private BigDecimal purchase_price;
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,###.##")
    private BigDecimal sale_price;
    @Column(nullable = false)
    private Integer stock;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    @Transient
    @Getter
    private static Integer number;

    public Products (RegisterProduct data, Integer count) {
        this.code = generateCode(count);
        this.name = data.name();
        this.description = data.description();
        this.image = data.image();
        this.size = data.size();
        this.brand = data.brand();
        this.purchase_price = data.purchase_price();
        this.sale_price = data.sale_price();
        this.stock = data.stock();
    }

    public void setNumber(Integer n) {
        number = n;
    }

    public static String generateCode(Integer count) {
        return "PROD00" + String.format("%03d", count);
    }
}
