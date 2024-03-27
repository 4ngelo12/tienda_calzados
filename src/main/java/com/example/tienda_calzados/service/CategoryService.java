package com.example.tienda_calzados.service;

import com.example.tienda_calzados.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity getAllCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }
}
