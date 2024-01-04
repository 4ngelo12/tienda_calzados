package com.example.tienda_calzados.model.sales;

import com.example.tienda_calzados.model.users.Users;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Table(name = "sales")
@Entity(name = "Sale")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 15, nullable = false)
    private String code;
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate purchase_date;
    @Column(length = 15, nullable = false)
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,###.##")
    private BigDecimal total;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Users users;

    public Sale(RegisterSale data, Users users) {
        this.code = UUID.randomUUID().toString().substring(0, 12);
        this.purchase_date = data.purchase_date();
        this.total = data.total();
        this.users = users;
    }
}
