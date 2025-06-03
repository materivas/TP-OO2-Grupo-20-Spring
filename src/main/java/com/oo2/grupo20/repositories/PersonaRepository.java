package com.oo2.grupo20.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo20.entities.Persona;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>, JpaSpecificationExecutor<Persona> {

    // --- Métodos CLAVE que realmente necesitas ---
    Optional<Persona> findByDni(Integer dni);
    
    Optional<Persona> findByEmail(String email);
    
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Persona p WHERE p.dni = :dni")
    boolean existsByDni(@Param("dni") Integer dni);
    
    boolean existsByEmail(String email);

    /**
    * Busca personas por año de nacimiento
    * @param anio Año de nacimiento (ej: 1990)
    * @return Lista de personas que nacieron en ese año
    */
    @Query("SELECT p FROM Persona p WHERE FUNCTION('YEAR', p.fechaDeNacimiento) = :anio")
    List<Persona> findByAnioNacimiento(@Param("anio") int anio);  // Agregar <Persona>

    // --- Paginación básica ---
    Page<Persona> findAll(Pageable pageable);
    
    // Para verificación rápida de existencia
    boolean existsById(Long id);

    // Para búsqueda case-insensitive
    List<Persona> findByNombreIgnoreCase(String nombre);
}