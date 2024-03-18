package com.example.tienda_calzados.service;

import com.example.tienda_calzados.dto.Body;
import com.example.tienda_calzados.dto.MetaData;
import com.example.tienda_calzados.dto.SendRequest;
import com.example.tienda_calzados.model.templates.Template;
import com.example.tienda_calzados.repository.TemplateRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import java.util.Optional;

@Service
public class SendMailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateRepository templateRepository;
    private static final String FROM = "Confety.Studios";

    public ResponseEntity<?> sendMessage(SendRequest sendRequest) {
        try {
            String messageContent = buildMessage(sendRequest);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(FROM);
            helper.setTo(sendRequest.to());
            helper.setSubject(sendRequest.subject());
            helper.setText(messageContent, true);
            mailSender.send(message);


        } catch (NotAcceptableStatusException e1) {
            return new ResponseEntity<Body>(new Body(e1.getReason()), HttpStatus.NOT_ACCEPTABLE);
        } catch (MessagingException e) {
            return new ResponseEntity<Body>(new Body(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Body>(new Body("Enviado exitosamente!"), HttpStatus.CREATED);
    }

    private String buildMessage(SendRequest sendRequest) {
        try {
            Optional<Template> template = templateRepository.findById(sendRequest.template().longValue());

            if (template.isPresent()) {
                String finalMessage = template.get().getContent();
                for (MetaData meta : sendRequest.metaData()) {
                    if (template.get().getVars().contains(meta.getKey())) {
                        finalMessage = finalMessage.replace("$[{" + meta.getKey() + "}]", meta.getValue());
                    } else {
                        throw new NotAcceptableStatusException("MetaData incompleta debe contener -> " + template.get().getVars());
                    }
                }

                return finalMessage;
            } else {
                throw new NotAcceptableStatusException("Template con Id " + sendRequest.template() + " no existe.");
            }
        } catch (NotAcceptableStatusException no) {
            throw no;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
