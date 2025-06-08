package com.oo2.grupo20.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo20.dto.ServicioDTO;
import com.oo2.grupo20.entities.Servicio;


@Repository("servicioRepository")
public interface IServicioRepository extends JpaRepository<Servicio, Serializable> {

	public abstract Optional<Servicio> findByNombreServicio(String nombreServicio);
	
	public abstract Optional<Servicio> findByIdServicio(Long idServicio);
	
	public abstract Optional<Servicio> findByDescripcion(String Descripcion);

	// Trae el servicio con sus días asociados
	@Query("SELECT s FROM Servicio s LEFT JOIN FETCH s.dias WHERE s.idServicio = :idServicio")
	public abstract Optional<Servicio> findServicioByIdWithDias(@Param("idServicio") Long idServicio);

	// Trae el servicio con sus turnos asociados
	@Query("SELECT s FROM Servicio s LEFT JOIN FETCH s.turnos WHERE s.idServicio = :idServicio")
	public abstract Optional<Servicio> findServicioByIdWithTurnos(@Param("idServicio") long idServicio);

	
	// Trae el servicio con sus turnos y dias asociados
	@Query("SELECT s FROM Servicio s LEFT JOIN FETCH s.dias LEFT JOIN FETCH s.turnos WHERE s.idServicio = :id")
	public abstract Optional<Servicio> findByIdServicioWithDiasAndTurnos(@Param("id") Long id);

	//Trae el establecimiento con sus empleados asociados.
	@Query("SELECT s FROM Servicio s LEFT JOIN FETCH s.establecimiento e JOIN FETCH e.empleados WHERE s.idServicio = :id")
	Optional<Servicio> findByIdWithEstablecimientoAndEmpleados(@Param("id") Long id);

	
	//Trae el establecimiento.
	@Query("SELECT s FROM Servicio s LEFT JOIN FETCH s.establecimiento WHERE s.idServicio = :id")
	Optional<Servicio> findByIdWithEstablecimiento(@Param("id") Long id);

	//Trae el establecimiento.
	@Query("SELECT s FROM Servicio s LEFT JOIN FETCH s.establecimiento WHERE s.idServicio = :id")
	Optional<Servicio> findByIdWithEstablecimiento2(@Param("id") Long id);
	

	
}

