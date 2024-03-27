package com.example.tienda_calzados.service;

import com.example.tienda_calzados.model.templates.RegisterTemplate;
import com.example.tienda_calzados.model.templates.Template;
import com.example.tienda_calzados.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class TemplateService {
    final Logger LOG = Logger.getLogger("com.example.tienda_calzados.service.TemplateService.class");
    @Autowired
    private TemplateRepository templateRepository;

    public Template saveTemplate(RegisterTemplate data) {
        try {
            var template = new Template(data);
            templateRepository.save(template);
            LOG.info("Plantilla guardada correctamente");
            return template;
        } catch (IOException e) {
            LOG.severe("Error al guardar la plantilla");
            throw new RuntimeException("Error al guardar la plantilla");
        }
    }
}
