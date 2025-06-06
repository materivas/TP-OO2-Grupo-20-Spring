package com.oo2.grupo20.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo20.entities.Cliente;
import com.oo2.grupo20.entities.Turno;

@Repository ("turnoRepository")
public interface ITurnoRepository extends JpaRepository<Turno, Long> {

	public abstract List<Turno> findByClienteDni(Integer dni);

	public abstract List<Turno> findByClienteEmail(String email);

	public abstract List<Turno> findByClienteApellido(String apellido);

	//public abstract List<Turno> findByDia_Fecha(LocalDate fecha);

	//public abstract List<Turno> findByDia_FechaBetween(LocalDate inicio, LocalDate fin);

 //   boolean existsByClienteAndDia_Fecha(Cliente cliente, LocalDate fecha);

  //  boolean existsByClienteAndDia_FechaAndHora(Cliente cliente, LocalDate fecha, LocalTime hora);

	/*
    // La consulta JPQL se traduce a: qué clientes tienen turno en determinada fecha
    @Query("SELECT DISTINCT t.cliente FROM Turno t WHERE t.dia.fecha = :fecha")
    List<Cliente> findClientesConTurnoEnFecha(@Param("fecha") LocalDate fecha);
    */
    //Métodos adicionales en base a las relaciones
    List<Turno> findByEmpleadoId(Long id);
    
   // List<Turno> findByServicioNombre(String nombre);
    
  //  List<Turno> findByEstado(String estado);
    
    public List<Turno> findAll();

}
