package com.oo2.grupo20.dto;

import java.time.LocalDate;
import java.util.Set;


public record ClienteRestDTO(
    Long id,
    String username,
    String password,
    String nombre,
    String apellido,
    Integer dni,
    String email,
    LocalDate fechaDeNacimiento,
    boolean estado,
    LocalDate fechaRegistro,
    Set<Long> turnosIds
) {}
