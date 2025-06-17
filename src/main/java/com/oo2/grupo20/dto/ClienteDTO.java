package com.oo2.grupo20.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.oo2.grupo20.entities.Turno;

import jakarta.validation.constraints.NotBlank;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ClienteDTO {

    private Long id;
    
    private Integer dni;
    private String nombre;
    private String apellido;
    
    private String email;
    private LocalDate fechaRegistro;
    boolean estado;
    
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    private Set<Turno> turnos = new HashSet<>();
}
