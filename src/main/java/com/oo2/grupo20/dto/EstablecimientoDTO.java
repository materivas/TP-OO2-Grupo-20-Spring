package com.oo2.grupo20.dto;



import java.util.HashSet;
import java.util.Set;

import com.oo2.grupo20.entities.Empleado;
import com.oo2.grupo20.entities.Servicio;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class EstablecimientoDTO {

	private Long idEstablecimiento;
	private String localidad;
	private String nombre;
	private Set<Empleado> empleados = new HashSet<>();
	private Set<Servicio> servicios = new HashSet<>();
	
	
	public EstablecimientoDTO (long idEstablecimiento,String nombre, String localidad) {
		
		setIdEstablecimiento(idEstablecimiento);
		this.nombre = nombre;
		this.localidad = localidad;
		
	}
	
	public EstablecimientoDTO (String nombre, String localidad) {
		
		this.nombre = nombre;
		this.localidad = localidad;
		
	}
	
	
	
}
