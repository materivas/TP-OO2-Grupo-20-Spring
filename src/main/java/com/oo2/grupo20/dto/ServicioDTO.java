package com.oo2.grupo20.dto;
import java.time.LocalTime;
import java.util.Set;

import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.entities.Establecimiento;
import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.entities.Turno;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ServicioDTO {
	
 
	private Long idServicio;
    
	@Size(max = 20, message ="El nombre del servicio no puede ser mas largo que 20 caracteres")
	private String nombreServicio;
	
	@Size(max= 50, message ="La descripcion del servicio no puede ser mas larga que 50 caracteres")
	private String descripcion;
	private int duracion;
	private double precio;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private Establecimiento establecimiento;
	private Set<Dia> dias;
	private Set<Turno> turnos;
	
	public static ServicioDTO toDTO(Servicio servicio) {
		ServicioDTO serviciodto=new ServicioDTO();
		serviciodto.setDescripcion(servicio.getDescripcion());
		serviciodto.setDuracion(servicio.getDuracion());
		serviciodto.setPrecio(servicio.getPrecio());
		serviciodto.setHoraInicio(servicio.getHoraInicio());
		serviciodto.setHoraFin(servicio.getHoraFin());
		serviciodto.setEstablecimiento(servicio.getEstablecimiento());
		serviciodto.setDias(servicio.getDias());
		serviciodto.setIdServicio(servicio.getIdServicio());
		serviciodto.setTurnos(servicio.getTurnos());
		
		return serviciodto;
	}
	
	public static Servicio fromDTO(ServicioDTO dto) {
	    Servicio servicio = new Servicio();
	    servicio.setIdServicio(dto.getIdServicio());
	    servicio.setNombreServicio(dto.getNombreServicio());
	    servicio.setDescripcion(dto.getDescripcion());
	    servicio.setDuracion(dto.getDuracion());
	    servicio.setPrecio(dto.getPrecio());
	    servicio.setHoraInicio(dto.getHoraInicio());
	    servicio.setHoraFin(dto.getHoraFin());
	    servicio.setEstablecimiento(dto.getEstablecimiento());
	    servicio.setDias(dto.getDias());
	    servicio.setTurnos(dto.getTurnos());
	    return servicio;
	}



	

}
