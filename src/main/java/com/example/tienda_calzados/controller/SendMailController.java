package com.example.tienda_calzados.controller;

import com.example.tienda_calzados.dto.SendRequest;
import com.example.tienda_calzados.service.SendMailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sendMail")
@CrossOrigin("*")
public class SendMailController {
    @Autowired
    private SendMailService sendMailService;

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody @Valid SendRequest sendRequest) {
        return sendMailService.sendMessage(sendRequest);
    }
}
