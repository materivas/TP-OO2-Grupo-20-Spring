package com.oo2.grupo20.dto;

import java.time.LocalDate;

public record EmpleadoRestDTO(
    Long id,
    String nombre,
    String apellido,
    Integer dni,
    String cuil,
    String email,
    String username,
    String password,
    LocalDate fechaDeNacimiento,
    boolean estado,
    String rol,
    Long establecimientoId,
    Long[] especialidadesIds
) {}
