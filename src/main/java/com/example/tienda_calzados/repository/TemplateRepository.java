package com.example.tienda_calzados.repository;

import com.example.tienda_calzados.model.templates.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {
}
