package com.oo2.grupo20.services.implementation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void enviarEmailHtml(String para, String asunto, String nombre, String fecha, String hora, String servicio) {
    	try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

            String contenidoHtml = """
            		<!DOCTYPE html>
            		<html>
            		<head>
            		    <meta charset="UTF-8">
            		    <style>
            		        body {
            		            font-family: Arial, sans-serif;
            		            background-color: #e9ecef;
            		            color: #212529;
            		            padding: 20px;
            		        }
            		        .container {
            		            background-color: #ffffff;
            		            border-radius: 10px;
            		            border: 2px solid #003366;
            		            padding: 30px;
            		            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            		            max-width: 600px;
            		            margin: 20px auto;
            		        }
            		        .header {
            		            background-color: #001f3f;
            		            color: white;
            		            padding: 15px;
            		            border-radius: 8px 8px 0 0;
            		            text-align: center;
            		        }
            		        .details {
            		            margin-top: 20px;
            		        }
            		        .details p {
            		            margin: 5px 0;
            		        }
            		        .footer {
            		            text-align: center;
            		            margin-top: 30px;
            		            font-size: 0.85em;
            		            color: #666;
            		        }
            		        .button {
            		            background-color: #007bff;
            		            color: white;
            		            padding: 10px 20px;
            		            margin-top: 20px;
            		            text-decoration: none;
            		            border-radius: 5px;
            		            display: inline-block;
            		        }
            		    </style>
            		</head>
            		<body>
            		    <div class="container">
            		        <div class="header">
            		            <h2>¡Turno Confirmado!</h2>
            		        </div>
            		        <p>Hola <strong>%s</strong>,</p>
            		        <p>Tu turno ha sido confirmado correctamente. Aquí están los detalles:</p>
            		        <div class="details">
            		            <p><strong>📅 Fecha:</strong> %s</p>
            		            <p><strong>⏰ Hora:</strong> %s</p>
            		            <p><strong>💼 Servicio:</strong> %s</p>
            		        </div>
            		        <a href="http://localhost:8080/turno/index"
            					style="display:inline-block;
            					padding:10px 20px;
						          background-color:#007bff;
						          color:#ffffff;
						          text-decoration:none;
						          border-radius:5px;
						          font-weight:bold;">
						   	Ver mis turnos
							</a>
            		        <div class="footer">
            		            <p>Este es un mensaje automático. Por favor no respondas a este correo.</p>
            		            <p>© 2025 Grupo 20 - Universidad Nacional</p>
            		        </div>
            		    </div>
            		</body>
            		</html>
            		""".formatted(nombre, fecha, hora, servicio); 

            helper.setTo(para);
            helper.setSubject(asunto);
            helper.setFrom(from);
            helper.setText(contenidoHtml, true); // HTML

            mailSender.send(mensaje);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
