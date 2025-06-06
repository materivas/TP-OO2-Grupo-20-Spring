package com.oo2.grupo20.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oo2.grupo20.entities.Dia;

public interface IDiaRepository extends JpaRepository<Dia, Serializable> {
	
	//public abstract Optional<Empleado> findByNombre(String nombre);
	
	public abstract Optional<Dia> findByFecha(LocalDate fecha);
	
	public abstract Optional<Dia> findById(long idDia);
	
	//traemos un dia y sus turnos asociados
	@Query("SELECT d FROM Dia d LEFT JOIN FETCH d.turnos WHERE d.idDia = :idDia")
	public abstract Dia findDiaConTurnosByIdDia(@Param("idDia") long idDia);
	
	

}
