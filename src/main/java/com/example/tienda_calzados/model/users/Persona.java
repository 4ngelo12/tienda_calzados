package com.example.tienda_calzados.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@MappedSuperclass
public class Persona {
    @Column(length = 60, nullable = false)
    protected String name;
    @Column(length = 60, nullable = false)
    protected String lastname;
    @Column(length = 70, nullable = false)
    protected String email;
    @Column(name = "birthday")
    protected LocalDate birthdate;
    @Column(length = 60, nullable = false)
    protected String password;
    @Column(length = 5, nullable = false, columnDefinition = "tinyint")
    protected Boolean active;
}
