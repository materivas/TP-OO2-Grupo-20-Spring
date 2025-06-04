package com.oo2.grupo20.repositories;

import java.io.Serializable;
import java.util.List;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo20.entities.Empleado;



@Repository ("empleadoRepository")
public interface IEmpleadoRepository extends JpaRepository <Empleado, Serializable> {
	
	public abstract Optional<Empleado> findByNombre(String nombre);
	
	public abstract Optional<Empleado> findByApellido (String apellido);
	
	public abstract Optional<Empleado> findByCUIL (String cuil);

	//Hacemos una Query para traer las especialidades asociadas a este CUIL
	@Query("SELECT e FROM Empleado e JOIN FETCH e.especialidades WHERE e.CUIL = :cuil")
	Optional<Empleado> findByCUILWithEspecialidades(@Param("cuil") String cuil);


	
	
	

}
