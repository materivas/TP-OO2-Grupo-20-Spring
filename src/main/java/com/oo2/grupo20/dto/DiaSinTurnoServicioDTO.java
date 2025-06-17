package com.oo2.grupo20.dto;

import java.time.LocalDate;
import java.util.Set;

import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.entities.Turno;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DiaSinTurnoServicioDTO {

	
	private Long idDia;
    private LocalDate fecha;
    
    public DiaSinTurnoServicioDTO( Long idDia, LocalDate fecha ) {
    	
    	this.setIdDia(idDia);
        this.fecha = fecha;
        
    	
    	
    }
    
    
	
}
