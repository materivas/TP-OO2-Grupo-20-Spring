package com.oo2.grupo20.dto;

import java.time.LocalTime;

public record TurnoCreateDTO(
	    LocalTime hora,
	    Long idCliente,
	    Long idEmpleado,
	    Long idServicio,
	    Long idDia
	) {}
