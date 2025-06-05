package com.oo2.grupo20.repositories;

import java.io.Serializable;
import java.util.List;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo20.entities.Establecimiento;



@Repository ("establecimientoRepository")
public interface IEstablecimientoRepository extends JpaRepository <Establecimiento, Serializable> {
	
	public abstract Optional<Establecimiento> findByIdEstablecimiento (long idEstablecimiento);
	
	public abstract Optional<Establecimiento> findByNombre (String nombre);

	//Hacemos una Query para traer los empleados asociados con este establecimiento
	@Query("SELECT e FROM Establecimiento e LEFT JOIN FETCH e.empleados WHERE e.idEstablecimiento = :idEstablecimiento")
	public abstract Optional<Establecimiento> findByIdWithEmpleados(long idEstablecimiento);
	
	//Faltaría agregar una query que busque los servicios asociados a este establecimiento

	
	
	

}