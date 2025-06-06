package com.oo2.grupo20.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.oo2.grupo20.entities.Cliente;
import com.oo2.grupo20.entities.Turno;

public interface ITurnoService {

	public List<Turno> findByClienteDni(String dni);

	public List<Turno> findByClienteEmail(String email);

	public List<Turno> findByClienteApellido(String apellido);

	public List<Turno> findByDia_Fecha(LocalDate fecha);

	public List<Turno> findByDia_FechaBetween(LocalDate inicio, LocalDate fin);

    boolean existsByClienteAndDia_Fecha(Cliente cliente, LocalDate fecha);

    boolean existsByClienteAndDia_FechaAndHora(Cliente cliente, LocalDate fecha, LocalTime hora);

    public List<Cliente> findClientesConTurnoEnFecha(@Param("fecha") LocalDate fecha);
	
    public List<Turno> findByEmpleadoId(Long id);
    
    public List<Turno> findByServicioNombre(String nombre);
    
    public List<Turno> findByEstado(String estado);

	public Turno save(Turno turno);

	public boolean deleteById(Long id);

	public List<Turno> findAll();
}
