package com.example.tienda_calzados.model.products;

import com.example.tienda_calzados.model.category.Category;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.lang.reflect.Method;
import java.math.BigDecimal;

@Entity(name = "Product")
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @Column(length = 5, nullable = false, columnDefinition = "tinyint")
    private Boolean active;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    @Transient
    @Getter
    private static Integer count;

    public Products (RegisterProduct data, Integer count, Category category) {
        this.code = generateCode(count);
        this.name = data.name();
        this.description = data.description();
        this.image = data.image();
        this.size = data.size();
        this.brand = data.brand();
        this.purchase_price = data.purchase_price();
        this.sale_price = data.sale_price();
        this.stock = data.stock();
        this.active = true;
        this.category = category;
    }

    public void setNumber(Integer number) {
        number++;
        count = number;
    }

    public static String generateCode(Integer count) {
        return "PROD00" + String.format("%03d", count);
    }

    public void deleteProduct() {
        this.active = false;
    }

    public void activateProduct() {
        this.active = true;
    }

    private <T> void assignIfNotNull(T value, Method setter) {
        if (value != null) {
            try {
                setter.invoke(this, value);
            } catch (Exception e) {
                e.printStackTrace(); // Manejo de excepciones apropiado
            }
        }
    }

    public void updateProduct(UpdateProduct data, Category category) {
        try {
            assignIfNotNull(data.name(), getClass().getMethod("setName", String.class));
            assignIfNotNull(data.description(), getClass().getMethod("setDescription", String.class));
            assignIfNotNull(data.image(), getClass().getMethod("setImage", String.class));
            assignIfNotNull(data.size(), getClass().getMethod("setSize", Integer.class));
            assignIfNotNull(data.brand(), getClass().getMethod("setBrand", String.class));
            assignIfNotNull(data.purchase_price(), getClass().getMethod("setPurchase_price", BigDecimal.class));
            assignIfNotNull(data.sale_price(),getClass().getMethod("setSale_price", BigDecimal.class));
            assignIfNotNull(data.stock(), getClass().getMethod("setSize", Integer.class));
            assignIfNotNull(category, getClass().getMethod("setCategory", Category.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public BigDecimal subTotal(Integer quantity, BigDecimal price) {
        return price.multiply(new BigDecimal(quantity));
    }
}
