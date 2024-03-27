package com.example.tienda_calzados.controller;

import com.example.tienda_calzados.model.templates.RegisterTemplate;
import com.example.tienda_calzados.model.templates.Template;
import com.example.tienda_calzados.service.TemplateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/templates")
@CrossOrigin("*")
@Tag(name = "Template", description = "Gestiona las plantillas de correo electrónico")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    @PostMapping
    public ResponseEntity<Template> saveTemplate(@RequestParam("file") MultipartFile templateFile ,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("vars") List<String> vars) {
        var data = new RegisterTemplate(templateFile, name, description, vars);
        var response = templateService.saveTemplate(data);
        return ResponseEntity.ok(response);
    }
}
