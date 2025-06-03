package com.oo2.grupo20.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonaResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private String email;
    private LocalDate fechaNacimiento;
    private Boolean estado;
}