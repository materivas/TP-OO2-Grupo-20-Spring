package com.oo2.grupo20.dto;

import java.time.LocalDate;
import java.time.LocalTime;


public record TurnoDTO(
    Long idTurno,
    LocalTime hora,
    String nombreCliente,
    String nombreEmpleado,
    String nombreServicio,
    LocalDate diaSemana
) {}