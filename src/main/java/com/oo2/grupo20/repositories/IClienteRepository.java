package com.oo2.grupo20.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo20.entities.Cliente;

@Repository ("clienteRepository")
public interface IClienteRepository extends JpaRepository <Cliente, Serializable> {
	
	public abstract Optional<Cliente> findByDni(Integer dni);
	
	public abstract List<Cliente> findByApellido(String apellido);
	
	public abstract List<Cliente> findByNombreAndApellido(String nombre, String apellido);
	
	//public abstract List<Cliente> findByFechaRegistroBetween(LocalDate inicio, LocalDate fin);
	
	@Query("SELECT c FROM Cliente c WHERE SIZE(c.turnos) > :minTurnos")
	List<Cliente> findClientesFrecuentes(@Param("minTurnos") int minTurnos);

	/*
	@Query("SELECT c FROM Cliente c JOIN c.turnos t WHERE t.fecha = :fecha")
	List<Cliente> findClientesConTurnoEnFecha(@Param("fecha") LocalDate fecha);
	*/
	boolean existsByDni(Integer dni);
	
	boolean existsByEmail(String email);
	
}
