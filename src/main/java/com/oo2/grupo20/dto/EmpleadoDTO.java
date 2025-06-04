package com.oo2.grupo20.dto;


import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter @Setter @NoArgsConstructor
public class EmpleadoDTO {
	
	private long idEmpleado;
	
	private String nombre;
	private String apellido;
	
	//Terminar de agregar validaciones al CUIL
	private String CUIL;
	
	private boolean estaDisponible;
	
	public EmpleadoDTO (long idEmpleado, String nombre, String apellido, String CUIL, boolean estaDisponible){

		this.setIdEmpleado (idEmpleado);
		this.nombre = nombre;
		this.apellido = apellido;
		this.CUIL = CUIL;
		this.estaDisponible = estaDisponible;
		
		}
	
	public EmpleadoDTO (String nombre, String apellido, String CUIL, boolean estaDisponible){

		this.nombre = nombre;
		this.apellido = apellido;
		this.CUIL = CUIL;
		this.estaDisponible = estaDisponible;
		
		}

}
