package com.oo2.grupo20.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor
public class ClienteDTO {

	private Long id;
    private String nombre;
    private String apellido;

    private LocalDate fechaRegistro;

    
    public ClienteDTO(String nombre, String apellido,LocalDate fechaRegistro) {
    	this.setId(id);
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaRegistro = fechaRegistro;
    }

}
