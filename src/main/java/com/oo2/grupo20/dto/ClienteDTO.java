package com.oo2.grupo20.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ClienteDTO {

    private Long id;
    private Integer dni;
    private String nombre;
    private String apellido;
    private String email;
    private LocalDate fechaRegistro;
    


}
