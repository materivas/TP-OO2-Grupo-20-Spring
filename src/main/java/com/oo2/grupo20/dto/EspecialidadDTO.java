package com.oo2.grupo20.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter @Setter @NoArgsConstructor
public class EspecialidadDTO {

	private long idEspecialidad;
	
	private String nombre;
	
	
	public EspecialidadDTO (long idEspecialidad, String nombre) {
		
		this.setIdEspecialidad (idEspecialidad);
		this.nombre = nombre;
		
	}
	
	public EspecialidadDTO (String nombre) {
		
		this.nombre = nombre;
		
	}
	
	
	
}
