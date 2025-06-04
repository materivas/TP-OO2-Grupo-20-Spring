package com.oo2.grupo20.dto;



import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class EstablecimientoDTO {

	private long idEstablecimiento;
	private String localidad;
	private String nombre;
	
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
