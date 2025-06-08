package com.oo2.grupo20.repositories;

import java.io.Serializable;
import java.util.List;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo20.entities.Especialidad;



@Repository ("especialidadRepository")
public interface IEspecialidadRepository extends JpaRepository <Especialidad, Serializable> {
	
	public abstract Optional<Especialidad> findByIdEspecialidad (Long idEspecialidad);
	
	public abstract Optional<Especialidad> findByNombre (String nombre);

	//Hacemos una Query para traer los empleados asociados con esta especialidad
	@Query("SELECT e FROM Especialidad e LEFT JOIN FETCH e.empleados WHERE e.idEspecialidad = :idEspecialidad")
	Optional<Especialidad> findByIdWithEmpleados(Long idEspecialidad);

	
	
	

}