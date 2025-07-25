package com.oo2.grupo20.dto;

import java.time.LocalTime;

public record ServicioRestDTO(
	    Long id,
	    String nombre,
	    String descricion,
	    int duracion,
	    double precio,
	    LocalTime horaFin,
	    LocalTime horaInicio,
	    Long establecimientoId,
	    Long[] diasIds
	    
	) {}
