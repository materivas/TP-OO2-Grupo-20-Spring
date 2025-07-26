package com.oo2.grupo20.controllers.rest;

import com.oo2.grupo20.dto.ClienteRestDTO;
import com.oo2.grupo20.entities.Cliente;
import com.oo2.grupo20.entities.Rol;
import com.oo2.grupo20.services.IClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Cliente", description = "Endpoints para manejar clientes")
public class ClienteRestController {

    private final IClienteService clienteService;

    public ClienteRestController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID")
    public ResponseEntity<ClienteRestDTO> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteEntityById(id);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        Set<Long> turnosIds = cliente.getTurnos()
                                      .stream()
                                      .map(turno -> turno.getIdTurno())
                                      .collect(Collectors.toSet());

        ClienteRestDTO dto = new ClienteRestDTO(
                cliente.getId(),
                cliente.getUsername(),
                cliente.getPassword(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDni(),
                cliente.getEmail(),
                cliente.getFechaDeNacimiento(),
                cliente.isEstado(),
                cliente.getFechaRegistro(),
                turnosIds
        );

        return ResponseEntity.ok(dto);
    }
    
    @PostMapping
    @Operation(summary = "Crear un cliente")
    public ResponseEntity<?> crearCliente(@RequestBody ClienteRestDTO dto) {

        if (dto.password() == null || dto.password().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "La contraseña no puede ser nula o vacía"));
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(dto.nombre());
        cliente.setApellido(dto.apellido());
        cliente.setDni(dto.dni());
        cliente.setEmail(dto.email());
        cliente.setUsername(dto.username());
        cliente.setPassword(dto.password());
        cliente.setFechaDeNacimiento(dto.fechaDeNacimiento());
        cliente.setFechaRegistro(dto.fechaRegistro());
        cliente.setEstado(dto.estado());

        try {
            cliente.setRol(Rol.CLIENTE); 
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Rol inválido"));
        }

        Cliente guardado = clienteService.insertOrUpdate(cliente);

        ClienteRestDTO respuesta = new ClienteRestDTO(
                guardado.getId(),
                guardado.getUsername(),
                guardado.getPassword(),
                guardado.getNombre(),
                guardado.getApellido(),
                guardado.getDni(),
                guardado.getEmail(),
                guardado.getFechaDeNacimiento(),
                guardado.isEstado(),
                guardado.getFechaRegistro(),
                new HashSet<>() // Si no tiene turnos aún
        );

        return ResponseEntity.ok(respuesta);
    }




}
