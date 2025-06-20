package com.oo2.grupo20.services.implementation;

import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.entities.Turno;
import com.oo2.grupo20.repositories.ITurnoRepository;
import com.oo2.grupo20.services.ITurnoService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service("turnoService")
@RequiredArgsConstructor
public class TurnoService implements ITurnoService {

    private final ITurnoRepository turnoRepository;
    
    @Override
    public Turno save(Turno turno) {
    	   // Validar horario laboral (8:00 - 18:00)
        if (turno.getHora().isBefore(LocalTime.of(8, 0)) || 
            turno.getHora().isAfter(LocalTime.of(17, 30))) {
            throw new IllegalArgumentException("El horario debe ser entre 08:00 y 17:30");
        }
        
        // Validar que la fecha sea futura (redundante pero segura)
        if (turno.getDia().getFecha().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha debe ser futura");
        }
        
        // Validar solapamiento
        if (turnoRepository.existsByDiaAndHoraAndEmpleado(
            turno.getDia(), 
            turno.getHora(), 
            turno.getEmpleado())) {
            throw new IllegalArgumentException("El empleado ya tiene un turno en ese horario");
        }
        
        return turnoRepository.save(turno);
    }
    
    
    
    // Calcula todas las horas libres para un servicio en un día específico.
    // Empieza desde la hora de inicio del servicio y va sumando bloques según su duración.
    // Si una hora ya tiene un turno, se salta. Devuelve solo las horas disponibles.
    @Override
    public List<LocalTime> generarHorasDisponibles(Servicio servicio, Dia dia) {
        List<Turno> turnosTomados = findByDia(dia); // método existente que trae los turnos de ese día
        Set<LocalTime> horasOcupadas = turnosTomados.stream()
            .map(Turno::getHora)
            .collect(Collectors.toSet());

        List<LocalTime> disponibles = new ArrayList<>();
        LocalTime hora = servicio.getHoraInicio();
        while (!hora.isAfter(servicio.getHoraFin().minusMinutes(servicio.getDuracion()))) {
            if (!horasOcupadas.contains(hora)) {
                disponibles.add(hora);
            }
            hora = hora.plusMinutes(servicio.getDuracion());
        }

        return disponibles;
    }

    
    @Override
    public List<Turno> findByDia(Dia dia) {
        return turnoRepository.findByDia(dia);
    }

    
    @Override
    public List<Turno> findByEmpleadoDni(Integer dni) {
        return turnoRepository.findByEmpleadoDni(dni);
    }


    @Override
    public void deleteById(Long id) {
            turnoRepository.deleteById(id);
    }
    
    @Override
    public List<Turno> findByClienteDni(Integer dni) {
        return turnoRepository.findByClienteDni(dni);
    }

    @Override
    public List<Turno> findByDia_Fecha(LocalDate fecha) {
        return turnoRepository.findByDia_Fecha(fecha);
    }

    @Override
    public List<Turno> findByDia_FechaBetween(LocalDate inicio, LocalDate fin) {
        return turnoRepository.findByDia_FechaBetween(inicio, fin);
    }

    @Override
    public List<Turno> findByEmpleadoId(Long id) {
        return turnoRepository.findByEmpleadoId(id);
    }

   public List<Turno> findAll(){
    	return turnoRepository.findAllWithRelations();
    }
   
   @Override
   public Optional<Turno> findById(Long id) {
       return turnoRepository.findById(id);
   }

   @Override
   public List<Turno> findAllWithRelations() {
       return turnoRepository.findAllWithRelations();
   }


}
