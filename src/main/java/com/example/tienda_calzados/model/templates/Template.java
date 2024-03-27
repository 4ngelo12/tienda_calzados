package com.example.tienda_calzados.model.templates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Table(name = "templates")
@Entity(name = "Template")
@Setter
@Getter
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50)
    private String description;
    @Column(length = 1000000)
    private String content;
    @Column(length = 100)
    private String vars;

    public Template() {
    }

    public Template(RegisterTemplate data) throws IOException {
        this.name = data.name();
        this.description = data.description();
        this.content = new String(data.file().getBytes());
        this.vars = data.vars().toString();
    }
}
