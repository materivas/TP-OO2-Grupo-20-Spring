package com.oo2.grupo20.dto;


import java.time.LocalDate;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter @Setter @NoArgsConstructor
public class EmpleadoDTO {

    private Long id;  // mejor nombre que idEmpleado

    private String nombre;
    private String apellido;

    @Size(min = 11, max = 11, message = "El CUIL debe tener 11 dígitos")
    @Pattern(regexp = "\\d{11}", message = "El CUIL debe contener solo números")
    private String CUIL;


    private Integer dni;
    private String email;
    private LocalDate fechaDeNacimiento;
    private boolean estado;

    public EmpleadoDTO(Long id, String nombre, String apellido, String CUIL, boolean estaDisponible) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.CUIL = CUIL;

    }

    public EmpleadoDTO(String nombre, String apellido, String CUIL, boolean estaDisponible) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.CUIL = CUIL;
 
    }
}
