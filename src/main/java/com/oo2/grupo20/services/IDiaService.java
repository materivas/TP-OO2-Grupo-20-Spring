package com.oo2.grupo20.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.oo2.grupo20.dto.DiaDTO;
import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.entities.Servicio;

public interface IDiaService {
	
	public List<Dia> getAll();
	
	public Dia insertOrUpdate(Dia dia);
	
	public boolean remove(long idDia);
	
	public Optional<DiaDTO> findByIdDia(long idDia);
	
	public Optional<DiaDTO> findByFecha(LocalDate fecha);
	
	public Optional<DiaDTO> findDiaByIdWithTurnos(long idDia);
	
	public Dia findOrCreateByFechaAndServicio(LocalDate fecha, Servicio servicio);

	public Optional<Dia> findByFechaAndServicio(LocalDate fecha, Servicio servicio);

	public Dia findOrCreateByFecha(LocalDate fecha);
	
	public List<LocalDate> findFechasDisponiblesPorServicio(Long idServicio);
	
	Dia getDiaEntityById(Long idDia);

	public Optional<Dia> findDiaEntityById(long idDia);
	
	
}
