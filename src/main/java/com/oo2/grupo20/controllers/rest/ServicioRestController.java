package com.oo2.grupo20.controllers.rest;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.oo2.grupo20.dto.ServicioRestDTO;
import com.oo2.grupo20.entities.Dia;

import com.oo2.grupo20.entities.Establecimiento;

import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.services.IDiaService;

import com.oo2.grupo20.services.IEstablecimientoService;
import com.oo2.grupo20.services.IServicioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/servicios")
@Tag(name = "Servicio", description = "Endpoints para manejar servicios")
public class ServicioRestController {
	
private final IServicioService servicioService;
private final IEstablecimientoService establecimientoService;
private final IDiaService diaService;
private final ModelMapper modelMapper = new ModelMapper();

public ServicioRestController(IServicioService servicioService, IEstablecimientoService establecimientoService,
								IDiaService diaService) {
	
	this.servicioService=servicioService;
	this.establecimientoService=establecimientoService;
	this.diaService=diaService;

}

@GetMapping("/{id}")
@Operation(summary ="Obtener Servicio por ID")
public ResponseEntity<ServicioRestDTO> getServicioById(@PathVariable Long id){
	Servicio s= servicioService.getServicioEntityById(id);
	
	if(s == null) {
		return ResponseEntity.notFound().build();
	}
	
	Long estId = s.getEstablecimiento() != null ? s.getEstablecimiento().getIdEstablecimiento() : null;
	Long []diaIds = s.getDias().stream().map(Dia::getIdDia).toArray(Long[]::new);
	
	ServicioRestDTO dto=new ServicioRestDTO(
			s.getIdServicio(),
			s.getNombreServicio(),
			s.getDescripcion(),
			s.getDuracion(),
			s.getPrecio(),
			s.getHoraFin(),
			s.getHoraInicio(),
			estId,
			diaIds
		);
	
	return ResponseEntity.ok(dto);
	
}

@PostMapping
@Operation(summary = "Crear un Servicio")
public ResponseEntity<?> crearServicio(@RequestBody ServicioRestDTO dto){
	
	Servicio servicio= new Servicio();
	servicio.setNombreServicio(dto.nombre());
	servicio.setDescripcion(dto.descricion());
	servicio.setDuracion(dto.duracion());
	servicio.setPrecio(dto.precio());
	servicio.setHoraFin(dto.horaFin());
	servicio.setHoraInicio(dto.horaInicio());
	
    if (dto.establecimientoId() != null) {
        Establecimiento est = establecimientoService.getEstablecimientoEntityById(dto.establecimientoId());
        servicio.setEstablecimiento(est);
    }
    
    if(dto.diasIds() != null) {
        Set<Dia> dias = Arrays.stream(dto.diasIds())
                .map(id -> diaService.findDiaEntityById(id).orElse(null)) 
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        servicio.setDias(dias);
    }

    
    Servicio guardado = servicioService.insertOrUpdate(servicio);
    
    Long estId = guardado.getEstablecimiento() != null
            ? guardado.getEstablecimiento().getIdEstablecimiento()
            : null;
    
    Long[] diaIds = guardado.getDias()
            .stream()
            .map(Dia::getIdDia)
            .toArray(Long[]::new);
    
    ServicioRestDTO respuesta =new ServicioRestDTO(
			guardado.getIdServicio(),
			guardado.getNombreServicio(),
			guardado.getDescripcion(),
			guardado.getDuracion(),
			guardado.getPrecio(),
			guardado.getHoraFin(),
			guardado.getHoraInicio(),
			estId,
			diaIds
		);
    
    return ResponseEntity.ok(respuesta);
	
}


}
