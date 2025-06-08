package com.oo2.grupo20.dto;
import java.time.LocalTime;
import java.util.Set;

import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.entities.Establecimiento;
import com.oo2.grupo20.entities.Turno;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ServicioSinTurnoDiaDTO {
	
 
	private Long idServicio;
    
	@Size(max = 20, message ="El nombre del servicio no puede ser mas largo que 20 caracteres")
	private String nombreServicio;
	
	@Size(max= 50, message ="La descripcion del servicio no puede ser mas larga que 50 caracteres")
	private String descripcion;
	private int duracion;
	private double precio;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private EstablecimientoBasicDTO establecimiento;



	

}
