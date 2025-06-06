package com.oo2.grupo20.services.implementation;

import com.oo2.grupo20.entities.Cliente;
import com.oo2.grupo20.entities.Turno;
import com.oo2.grupo20.repositories.ITurnoRepository;
import com.oo2.grupo20.services.ITurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service("turnoService")
@RequiredArgsConstructor
public class TurnoService implements ITurnoService {

    private final ITurnoRepository turnoRepository;

    @Override
    public Turno save(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            turnoRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
    
    @Override
    public List<Turno> findByClienteDni(String dni) {
        return turnoRepository.findByClienteDni(dni);
    }

    @Override
    public List<Turno> findByClienteEmail(String email) {
        return turnoRepository.findByClienteEmail(email);
    }

    @Override
    public List<Turno> findByClienteApellido(String apellido) {
        return turnoRepository.findByClienteApellido(apellido);
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
    public boolean existsByClienteAndDia_Fecha(Cliente cliente, LocalDate fecha) {
        return turnoRepository.existsByClienteAndDia_Fecha(cliente, fecha);
    }

    @Override
    public boolean existsByClienteAndDia_FechaAndHora(Cliente cliente, LocalDate fecha, LocalTime hora) {
        return turnoRepository.existsByClienteAndDia_FechaAndHora(cliente, fecha, hora);
    }

    @Override
    public List<Cliente> findClientesConTurnoEnFecha(LocalDate fecha) {
        return turnoRepository.findClientesConTurnoEnFecha(fecha);
    }

    @Override
    public List<Turno> findByEmpleadoId(Long id) {
        return turnoRepository.findByEmpleadoId(id);
    }

    @Override
    public List<Turno> findByServicioNombre(String nombre) {
        return turnoRepository.findByServicioNombre(nombre);
    }

    @Override
    public List<Turno> findByEstado(String estado) {
        return turnoRepository.findByEstado(estado);
    }

    public List<Turno> findAll(){
    	return turnoRepository.findAll();
    };
}
