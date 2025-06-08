package com.oo2.grupo20.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.oo2.grupo20.entities.Turno;

public interface ITurnoService {

	public List<Turno> findByClienteDni(Integer dni);

	public List<Turno> findByDia_Fecha(LocalDate fecha);

	public List<Turno> findByDia_FechaBetween(LocalDate inicio, LocalDate fin);

	public List<Turno> findByEmpleadoId(Long id);

	public Optional<Turno> findById(Long id);

	public Turno save(Turno turno);

	public void deleteById(Long id);

	public List<Turno> findAll();

	

}
