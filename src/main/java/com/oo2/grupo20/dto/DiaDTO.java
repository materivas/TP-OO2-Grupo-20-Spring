package com.oo2.grupo20.dto;

import java.time.LocalDate;
import java.util.Set;

import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.entities.Turno;

import jakarta.validation.constraints.Future;
import lombok.Data;

@Data
public class DiaDTO {
    private Long idDia;
    @Future(message = "La fecha debe ser futura") 
    private LocalDate fecha;
    private Servicio servicio;
    private Set<Turno> turnos;

    public DiaDTO() {}

    public DiaDTO(long idDia, LocalDate fecha) {
        this.idDia = idDia;
        this.fecha = fecha;
    }
    
    public static DiaDTO toDTO(Dia dia) {
    	DiaDTO diadto=new DiaDTO();
    	
    	diadto.setIdDia(dia.getIdDia());
    	diadto.setFecha(dia.getFecha());
    	diadto.setServicio(dia.getServicio());
    	diadto.setTurnos(dia.getTurnos());
    	return diadto;
    }

    public static Dia fromDTO(DiaDTO dto) {
    	Dia dia= new Dia();
    	
    	dia.setIdDia(dto.getIdDia());
    	dia.setFecha(dto.getFecha());
    	dia.setServicio(dto.getServicio());
    	dia.setTurnos(dto.getTurnos());
    	dia.setServicio(dto.getServicio());
    	return dia;
    	}
    
    

}


