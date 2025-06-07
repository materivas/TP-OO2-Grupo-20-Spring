package com.oo2.grupo20.dto;

import java.time.LocalDate;

import com.oo2.grupo20.entities.Servicio;

import lombok.Data;

@Data
public class DiaDTO {

    private long idDia;
    private LocalDate fecha;
    private Servicio servicio;


    // Constructor vacío
    public DiaDTO() {
    }

    // Constructor completo
    public DiaDTO(long idDia, LocalDate fecha) {
        this.idDia = idDia;
        this.fecha = fecha;
    }
}

