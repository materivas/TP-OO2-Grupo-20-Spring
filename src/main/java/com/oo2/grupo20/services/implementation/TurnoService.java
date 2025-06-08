package com.oo2.grupo20.services.implementation;

import com.oo2.grupo20.entities.Turno;
import com.oo2.grupo20.repositories.ITurnoRepository;
import com.oo2.grupo20.services.ITurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service("turnoService")
@RequiredArgsConstructor
public class TurnoService implements ITurnoService {

    private final ITurnoRepository turnoRepository;

    @Override
    public Turno save(Turno turno) {
        return turnoRepository.save(turno);
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
    	return turnoRepository.findAll();
    }
   
   @Override
   public Optional<Turno> findById(Long id) {
       return turnoRepository.findById(id);
   }


}
