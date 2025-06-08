package com.oo2.grupo20.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo20.entities.Turno;

@Repository ("turnoRepository")
public interface ITurnoRepository extends JpaRepository<Turno, Long> {

	public abstract List<Turno> findByClienteDni(Integer dni);
	
	public abstract List<Turno> findByDia_Fecha(LocalDate fecha);

	public abstract List<Turno> findByDia_FechaBetween(LocalDate inicio, LocalDate fin);

    List<Turno> findByEmpleadoId(Long id);
    
    public void deleteById(Long id);
    
    public List<Turno> findAll();
    
    public Optional<Turno> findById(Long id);

}
