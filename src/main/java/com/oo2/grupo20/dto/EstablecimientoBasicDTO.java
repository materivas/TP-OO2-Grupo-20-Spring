package com.oo2.grupo20.dto;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class EstablecimientoBasicDTO {
	
	
    private Long idEstablecimiento;
    private String nombre;
    private String localidad;
    
    public EstablecimientoBasicDTO (long idEstablecimiento,String nombre, String localidad) {
		
		this.setIdEstablecimiento(idEstablecimiento);
		this.nombre = nombre;
		this.localidad = localidad;
		
	}
	
	public EstablecimientoBasicDTO (String nombre, String localidad) {
		
		this.nombre = nombre;
		this.localidad = localidad;
		
	}
    
    
}