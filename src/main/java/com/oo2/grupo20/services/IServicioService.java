package com.oo2.grupo20.services;

import java.util.List;
import java.util.Optional;


import com.oo2.grupo20.dto.ServicioDto;
import com.oo2.grupo20.entities.Servicio;

public interface IServicioService {
	
	
	public List<Servicio> getAll();
	
	public Servicio insertOrUpdate(Servicio servicio);
	
	public boolean remove(long idServicio);
	
	public Optional<ServicioDto> findByNombreServicio(String nombreServicio);
	
	public Optional<ServicioDto> findByDescripcion(String descripcion);
	
	

}
