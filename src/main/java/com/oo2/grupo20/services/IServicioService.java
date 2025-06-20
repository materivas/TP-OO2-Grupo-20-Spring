package com.oo2.grupo20.services;

import java.util.List;
import java.util.Optional;

import com.oo2.grupo20.dto.ServicioConDiaDTO;
import com.oo2.grupo20.dto.ServicioDTO;
import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.dto.ServicioSinTurnoDiaDTO;

public interface IServicioService {
	
	
	public List<Servicio> getAll();
	
	public Servicio insertOrUpdate(Servicio servicio);
	
	public boolean remove(Long idServicio);
	
	public Optional<ServicioDTO> findByNombreServicio(String nombreServicio);
	
	public Optional<ServicioDTO> findByDescripcion(String descripcion);
	
	public Optional<ServicioDTO> findByIdServicio(Long idServicio);
	
	public Optional<ServicioDTO> findByIdServicioWithDias(Long idServicio);
	
	public Optional<ServicioConDiaDTO> findByIdServicioWithDias2(Long idServicio);
	
	public Optional<ServicioDTO> findByIdServicioWithDiasAndTurnos(Long id);
	
	public Optional<ServicioDTO> findByIdServicioWithEstablecimientoAndEmpleados(Long id);
	
	public Optional<ServicioDTO> findByIdWithEstablecimiento(Long id);
	
	public Optional<ServicioSinTurnoDiaDTO> findByIdWithEstablecimiento2(Long id);
	
	public void generarDiasParaServicio(Servicio servicio);
	
	Servicio getServicioEntityById(Long id);


}
