package com.oo2.grupo20.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.oo2.grupo20.entities.Servicio;


@Repository("servicioRepository")
public interface IServicioRepository extends JpaRepository<Servicio, Serializable> {

	public abstract Optional<Servicio> findByNombreServicio(String nombreServicio);
	
	public abstract Optional<Servicio> findByIdServicio(long idServicio);
	
	public abstract Optional<Servicio> findByDescripcion(String Descripcion);

	// Trae el servicio con sus días asociados
	@Query("SELECT s FROM Servicio s LEFT JOIN FETCH s.dias WHERE s.idServicio = :idServicio")
	public abstract Servicio findServicioConDiasByIdServicio(@Param("idServicio") long idServicio);

	// Trae el servicio con sus turnos asociados
	@Query("SELECT s FROM Servicio s LEFT JOIN FETCH s.turnos WHERE s.idServicio = :idServicio")
	public abstract Servicio findServicioConTurnosByIdServicio(@Param("idServicio") long idServicio);

	
}

