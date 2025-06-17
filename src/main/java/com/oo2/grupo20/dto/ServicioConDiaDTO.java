package com.oo2.grupo20.dto;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.entities.Establecimiento;
import com.oo2.grupo20.entities.Turno;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ServicioConDiaDTO {
	
 
	private Long idServicio;
	private String nombreServicio;
	private String descripcion;
	private int duracion;
	private double precio;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private EstablecimientoBasicDTO establecimiento;
	private Set<DiaSinTurnoServicioDTO> dias = new HashSet<>();


	

}
