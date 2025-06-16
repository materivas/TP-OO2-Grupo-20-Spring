package com.oo2.grupo20.dto;

import java.time.LocalDate;
import java.util.Set;

import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.entities.Servicio;
import lombok.Data;
import com.oo2.grupo20.entities.Turno;

@Data
public class DiaDTO {
    private Long idDia;
    private LocalDate fecha;
    private Servicio servicio;
    private Set<Turno> turnos;

    public DiaDTO() {
    }

    public DiaDTO(long idDia, LocalDate fecha) {
        this.idDia = idDia;
        this.fecha = fecha;
    }

}


