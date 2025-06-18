package com.oo2.grupo20.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo20.entities.Persona;

import java.util.Optional;

@Repository("personaRepository")
public interface IPersonaRepository extends JpaRepository<Persona, Serializable> {

	Optional<Persona> findByUsername(String username);

    boolean existsByDni(Integer dni);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<Persona> findByDni(Integer dni);

    Optional<Persona> findByEmail(String email);
    



}