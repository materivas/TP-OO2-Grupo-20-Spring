package com.oo2.grupo20.services;

import java.util.List;
import java.util.Optional;


import com.oo2.grupo20.dto.ServicioDTO;
import com.oo2.grupo20.entities.Servicio;

public interface IServicioService {
	
	
	public List<Servicio> getAll();
	
	public Servicio insertOrUpdate(Servicio servicio);
	
	public boolean remove(Long idServicio);
	
	public Optional<ServicioDTO> findByNombreServicio(String nombreServicio);
	
	public Optional<ServicioDTO> findByDescripcion(String descripcion);
	
	public Optional<ServicioDTO> findByIdServicio(Long idServicio);
	
	public Optional<ServicioDTO> findByIdServicioWithDias(Long idServicio);
	
	public Optional<ServicioDTO> findByIdServicioWithDiasAndTurnos(Long id);
	
	Servicio getServicioEntityById(Long id);


}
