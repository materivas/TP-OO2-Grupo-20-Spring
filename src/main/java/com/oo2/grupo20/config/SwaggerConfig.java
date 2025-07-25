package com.oo2.grupo20.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

// Info general de la API
@OpenAPIDefinition(
    info = @Info(
        title = "API de Gestión de Turnos Médicos",
        version = "1.0",
        description = "Sistema para gestionar turnos médicos, empleado, clientes,etc"
        ),
    servers = {
        @Server(url = "http://localhost:8080", description = "Servidor local")
    }
)
@Configuration
public class SwaggerConfig {
    
}
