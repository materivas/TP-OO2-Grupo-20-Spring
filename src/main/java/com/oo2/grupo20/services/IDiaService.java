package com.oo2.grupo20.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.oo2.grupo20.dto.DiaDTO;
import com.oo2.grupo20.entities.Dia;

public interface IDiaService {
	
	public List<Dia> getAll();
	
	public Dia insertOrUpdate(Dia dia);
	
	public boolean remove(long idDia);
	
	public Optional<DiaDTO> findByIdDia(long idDia);
	
	public Optional<DiaDTO> findByFecha(LocalDate fecha);
	
	public Optional<DiaDTO> findDiaByIdWithTurnos(long idDia);
	
	public Dia findOrCreateByFecha(LocalDate fecha);
}
