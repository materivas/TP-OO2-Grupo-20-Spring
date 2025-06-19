package com.oo2.grupo20.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.entities.Empleado;
import com.oo2.grupo20.entities.Turno;

@Repository ("turnoRepository")
public interface ITurnoRepository extends JpaRepository<Turno, Long> {

	public abstract List<Turno> findByClienteDni(Integer dni);
	
	public abstract List<Turno> findByDia_Fecha(LocalDate fecha);

	public abstract List<Turno> findByDia_FechaBetween(LocalDate inicio, LocalDate fin);

    List<Turno> findByEmpleadoId(Long id);
    
    public void deleteById(Long id);
    
    List<Turno> findByEmpleadoDni(Integer dni);
    
    public List<Turno> findAll();
    
    public Optional<Turno> findById(Long id);
    
    @Query("SELECT t FROM Turno t LEFT JOIN FETCH t.cliente LEFT JOIN FETCH t.empleado LEFT JOIN FETCH t.dia")
    List<Turno> findAllWithRelations();

	 boolean existsByDiaAndHoraAndEmpleado(Dia dia, LocalTime hora, Empleado empleado);
	 
	 List<Turno> findByDia(Dia dia);



}
