package com.oo2.grupo20.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.entities.Turno;

public interface ITurnoService {

	public List<Turno> findByClienteDni(Integer dni);

	public List<Turno> findByDia_Fecha(LocalDate fecha);

	public List<Turno> findByDia_FechaBetween(LocalDate inicio, LocalDate fin);

	public List<Turno> findByEmpleadoId(Long id);

	public Optional<Turno> findById(Long id);
	
	List<Turno> findByEmpleadoDni(Integer dni);


	public Turno save(Turno turno);

	public void deleteById(Long id);

	public List<Turno> findAll();

	public List<Turno> findAllWithRelations();

	List<Turno> findByDia(Dia dia);

	public List<LocalTime> generarHorasDisponibles(Servicio servicio, Dia dia);

}
