package com.oo2.grupo20.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.oo2.grupo20.entities.Persona;

@Transactional
public interface IPersonaService {

	public Optional<Persona> findByDni(Integer dni);
    
	public Optional<Persona> findByEmail(String email);

    boolean existsByDni(@Param("dni") Integer dni);
    
    boolean existsByEmail(String email);

    public List<Persona> findByAnioNacimiento(@Param("anio") int anio);  

    public Page<Persona> findAll(Pageable pageable);
    
    public boolean existsById(Long id);

    public List<Persona> findByNombreIgnoreCase(String nombre);

}