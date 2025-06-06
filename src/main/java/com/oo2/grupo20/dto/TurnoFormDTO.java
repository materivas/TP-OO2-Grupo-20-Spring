package com.oo2.grupo20.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter @NoArgsConstructor
public class TurnoFormDTO {

    private long servicioId;
    private LocalDate fecha;
    private LocalTime hora;
}
