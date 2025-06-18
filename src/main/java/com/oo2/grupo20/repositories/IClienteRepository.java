package com.oo2.grupo20.repositories;

import java.io.Serializable;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo20.entities.Cliente;

@Repository ("clienteRepository")
public interface IClienteRepository extends JpaRepository <Cliente, Serializable> {
	
	public abstract Optional<Cliente> findByDni(Integer dni);
	
	public abstract Optional<Cliente> findByApellido(String apellido);
	
	public abstract Optional<Cliente> findByNombreAndApellido(String nombre, String apellido);

	Optional<Cliente> findByEmail(String email);

	boolean existsByDni(Integer dni);
	
	boolean existsByEmail(String email);
	
}
