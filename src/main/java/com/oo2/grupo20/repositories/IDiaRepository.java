package com.oo2.grupo20.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.entities.Servicio;

public interface IDiaRepository extends JpaRepository<Dia, Serializable> {
	
	//public abstract Optional<Empleado> findByNombre(String nombre);
	
	public abstract Optional<Dia> findByFecha(LocalDate fecha);
	
	public abstract Optional<Dia> findByIdDia(long idDia);
	
	//traemos un dia y sus turnos asociados
	@Query("SELECT d FROM Dia d LEFT JOIN FETCH d.turnos WHERE d.idDia = :idDia")
	public abstract Optional<Dia> findDiaByIdWithTurnos(@Param("idDia") long idDia);
	
	public Dia findOrCreateByFechaAndServicio(LocalDate fecha, Servicio servicio);
	
	Optional<Dia> findByFechaAndServicio(LocalDate fecha, Servicio servicio);
	
	public Dia findOrCreateByFecha(LocalDate fecha);
	
	@Query("SELECT d.fecha FROM Dia d WHERE d.servicio = :servicio")
	List<LocalDate> findDiasDisponiblesPorServicio(@Param("servicio") Servicio servicio);


	
}
