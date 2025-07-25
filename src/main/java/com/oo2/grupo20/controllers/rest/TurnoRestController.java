package com.oo2.grupo20.controllers.rest;

import com.oo2.grupo20.dto.TurnoCreateDTO;
import com.oo2.grupo20.dto.TurnoDTO;
import com.oo2.grupo20.entities.Cliente;
import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.entities.Empleado;
import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.entities.Turno;
import com.oo2.grupo20.services.ITurnoService;
import com.oo2.grupo20.services.implementation.ClienteService;
import com.oo2.grupo20.services.implementation.DiaService;
import com.oo2.grupo20.services.implementation.EmpleadoService;
import com.oo2.grupo20.services.implementation.ServicioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
@Tag(name = "Turno API", description = "Operaciones relacionadas con turnos")
@RequiredArgsConstructor
public class TurnoRestController {

    private final ITurnoService turnoService;
    private final ClienteService clienteService;
    private final EmpleadoService empleadoService;
    private final ServicioService servicioService;
    private final DiaService diaService;
    
    @Operation(summary = "Obtener todos los turnos")
    @GetMapping
    public ResponseEntity<List<TurnoDTO>> getAllTurnos() {
        List<TurnoDTO> dtoList = turnoService.findAll().stream()
            .map(this::toDTO)
            .toList();
        return ResponseEntity.ok(dtoList);
    }

   
  
    
    
    @Operation(summary = "Crear un nuevo turno")
    @PostMapping
    public ResponseEntity<?> crearTurno(@RequestBody TurnoCreateDTO dto) {
        Turno turno = new Turno();
        turno.setHora(dto.hora());

        Cliente cliente = clienteService.getClienteEntityById(dto.idCliente());
        Empleado empleado = empleadoService.getEmpleadoEntityById(dto.idEmpleado());
        Servicio servicio = servicioService.getServicioEntityById(dto.idServicio());
        Dia dia = diaService.getDiaEntityById(dto.idDia());
        
        turno.setCliente(cliente);
        turno.setEmpleado(empleado);
        turno.setServicio(servicio);
        turno.setDia(dia);

        turnoService.save(turno);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    private TurnoDTO toDTO(Turno turno) {
        return new TurnoDTO(
            turno.getIdTurno(),
            turno.getHora(),
            turno.getCliente().getNombre(),   // Asegurate que tenga getNombre()
            turno.getEmpleado().getNombre(),
            turno.getServicio().getNombreServicio(),
            turno.getDia().getFecha()
        );    
    }
}
