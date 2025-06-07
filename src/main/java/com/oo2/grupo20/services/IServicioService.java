package com.oo2.grupo20.services;

import java.util.List;
import java.util.Optional;


import com.oo2.grupo20.dto.ServicioDTO;
import com.oo2.grupo20.entities.Servicio;

public interface IServicioService {
	
	
	public List<Servicio> getAll();
	
	public Servicio insertOrUpdate(Servicio servicio);
	
	public boolean remove(long idServicio);
	
	public Optional<ServicioDTO> findByNombreServicio(String nombreServicio);
	
	public Optional<ServicioDTO> findByDescripcion(String descripcion);
	
	public Optional<ServicioDTO> findByIdServicio(long idServicio);
	
	public Optional<ServicioDTO> findByIdServicioWithDias(long idServicio);
	
	public Optional<ServicioDTO> findByIdServicioWithTurnos(long idServicio);
	
	

}
