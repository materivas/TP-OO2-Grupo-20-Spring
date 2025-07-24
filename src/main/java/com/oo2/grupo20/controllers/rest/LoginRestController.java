package com.oo2.grupo20.controllers.rest;

import com.oo2.grupo20.dto.LoginDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Endpoints para login REST")
public class LoginRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    
    @PostMapping("/login")
    @Operation(summary = "Login de usuario", description = "Permite autenticar un usuario enviando username y password en formato JSON")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());

        try {
            Authentication authentication = authenticationManager.authenticate(authToken);
            return ResponseEntity.ok("Autenticado correctamente como: " + authentication.getName());
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}
