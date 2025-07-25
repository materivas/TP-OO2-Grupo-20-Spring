package com.oo2.grupo20.controllers.rest;

import com.oo2.grupo20.dto.EmpleadoRestDTO;
import com.oo2.grupo20.entities.Empleado;
import com.oo2.grupo20.entities.Especialidad;
import com.oo2.grupo20.entities.Establecimiento;
import com.oo2.grupo20.entities.Rol;
import com.oo2.grupo20.exceptions.EmpleadoTieneTurnoException;
import com.oo2.grupo20.services.IEmpleadoService;
import com.oo2.grupo20.services.IEspecialidadService;
import com.oo2.grupo20.services.IEstablecimientoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empleados")
@Tag(name = "Empleado", description = "Endpoints para manejar empleados")
public class EmpleadoRestController {

    private final IEmpleadoService empleadoService;
    private final IEstablecimientoService establecimientoService;
    private final IEspecialidadService especialidadService;
    private final ModelMapper modelMapper = new ModelMapper();

    public EmpleadoRestController(IEmpleadoService empleadoService,
                                  IEstablecimientoService establecimientoService,
                                  IEspecialidadService especialidadService) {
        this.empleadoService = empleadoService;
        this.establecimientoService = establecimientoService;
        this.especialidadService = especialidadService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener empleado por ID")
    public ResponseEntity<EmpleadoRestDTO> getEmpleadoById(@PathVariable Long id) {
        Empleado e = empleadoService.getEmpleadoEntityById(id);

        if (e == null) {
            return ResponseEntity.notFound().build();
        }

        Long estId = e.getEstablecimiento() != null ? e.getEstablecimiento().getIdEstablecimiento() : null;
        Long[] espIds = e.getEspecialidades()
                         .stream()
                         .map(Especialidad::getIdEspecialidad)
                         .toArray(Long[]::new);

        EmpleadoRestDTO dto = new EmpleadoRestDTO(
                e.getId(),
                e.getNombre(),
                e.getApellido(),
                e.getDni(),
                e.getCUIL(),
                e.getEmail(),
                e.getUsername(),
                e.getPassword(),
                e.getFechaDeNacimiento(),
                e.isEstado(),
                e.getRol() != null ? e.getRol().toString() : null,
                estId,
                espIds
        );

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Crear un empleado")
    public ResponseEntity<?> crearEmpleado(@RequestBody EmpleadoRestDTO dto) {

        if (dto.password() == null || dto.password().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "La contraseña no puede ser nula o vacía"));
        }

        //Seteamos/mapeamos manualmente el EmpleadoRestDTO
        Empleado empleado = new Empleado();
        empleado.setNombre(dto.nombre());
        empleado.setApellido(dto.apellido());
        empleado.setDni(dto.dni());
        empleado.setCUIL(dto.cuil());  
        empleado.setEmail(dto.email());
        empleado.setUsername(dto.username());
        empleado.setPassword(dto.password());
        empleado.setFechaDeNacimiento(dto.fechaDeNacimiento());
        empleado.setEstado(dto.estado());

        // Convertir rol string a enum
        if (dto.rol() != null) {
            try {
                empleado.setRol(Rol.valueOf(dto.rol()));
            } catch (IllegalArgumentException ex) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Rol inválido: " + dto.rol()));
            }
        }

        if (dto.establecimientoId() != null) {
            Establecimiento est = establecimientoService.getEstablecimientoEntityById(dto.establecimientoId());
            empleado.setEstablecimiento(est);
        }

        if (dto.especialidadesIds() != null && dto.especialidadesIds().length > 0) {
            Set<Especialidad> especialidades = Arrays.stream(dto.especialidadesIds())
                    .map(id -> especialidadService.findByIdEspecialidad(id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            empleado.setEspecialidades(especialidades);
        }

        Empleado guardado = empleadoService.insertOrUpdate(empleado);

        Long estId = guardado.getEstablecimiento() != null
                ? guardado.getEstablecimiento().getIdEstablecimiento()
                : null;

        Long[] espIds = guardado.getEspecialidades()
                .stream()
                .map(Especialidad::getIdEspecialidad)
                .toArray(Long[]::new);

        EmpleadoRestDTO respuesta = new EmpleadoRestDTO(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getApellido(),
                guardado.getDni(),
                guardado.getCUIL(),
                guardado.getEmail(),
                guardado.getUsername(),
                guardado.getPassword(),
                guardado.getFechaDeNacimiento(),
                guardado.isEstado(),
                guardado.getRol() != null ? guardado.getRol().toString() : null,
                estId,
                espIds
        );

        return ResponseEntity.ok(respuesta);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un empleado por ID")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id) {
        Empleado empleado = empleadoService.getEmpleadoEntityById(id);

        if (empleado == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            boolean eliminado = empleadoService.remove(id);
            if (eliminado) {
                return ResponseEntity.ok(Map.of("mensaje", "Empleado eliminado correctamente"));
            } else {
                return ResponseEntity.internalServerError()
                        .body(Map.of("error", "Ocurrió un error al intentar eliminar el empleado"));
            }
        } catch (EmpleadoTieneTurnoException ex) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", ex.getMessage()));
        }
    }

    
    
    
    
}
