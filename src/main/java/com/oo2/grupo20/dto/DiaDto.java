package com.oo2.grupo20.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class DiaDto {

    private long idDia;
    private LocalDate fecha;

    // Constructor 
    public DiaDto(long idDia, LocalDate fecha) {
        this.idDia = idDia;
        this.fecha = fecha;
        
    }

}
