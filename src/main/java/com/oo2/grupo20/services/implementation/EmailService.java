package com.oo2.grupo20.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;
    
    public void enviarEmail(String para, String asunto, String cuerpo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(para);
        mensaje.setSubject(asunto);
        mensaje.setText(cuerpo);
        mensaje.setFrom(from); // debe coincidir con spring.mail.username

        mailSender.send(mensaje);
    }
    
    /*
    @PostConstruct
    public void testEmail() {
        enviarEmail("testspringboot55@gmail.com", "Prueba", "Este es un mensaje de prueba");
    }
     */
    
}