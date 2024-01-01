package com.example.tienda_calzados.model.category;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Category")
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10, nullable = false)
    private String nombre;
}
