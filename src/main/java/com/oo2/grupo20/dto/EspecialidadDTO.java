package com.oo2.grupo20.dto;

import java.util.HashSet;
import java.util.Set;

import com.oo2.grupo20.entities.Empleado;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter @Setter @NoArgsConstructor
public class EspecialidadDTO {

	private Long idEspecialidad;
	
	private String nombre;
	private Set<Empleado> empleados = new HashSet<>();
	
	public EspecialidadDTO (long idEspecialidad, String nombre) {
		
		this.setIdEspecialidad (idEspecialidad);
		this.nombre = nombre;
		
	}
	
	public EspecialidadDTO (String nombre) {
		
		this.nombre = nombre;
		
	}
	
	
	
}
