package com.oo2.grupo20.dto;
import java.time.LocalTime;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ServicioDTO {
	
 
	private long idServicio;
    
	@Size(max = 20, message ="El nombre del servicio no puede ser mas largo que 20 caracteres")
	private String nombreServicio;
	
	@Size(max= 50, message ="La descripcion del servicio no puede ser mas larga que 50 caracteres")
	private String descripcion;
	private int duracion;
	private double precio;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private EstablecimientoDTO establecimiento;


	

}
