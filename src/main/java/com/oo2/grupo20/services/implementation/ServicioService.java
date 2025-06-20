package com.oo2.grupo20.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.repositories.IDiaRepository;
import com.oo2.grupo20.repositories.IServicioRepository;
import com.oo2.grupo20.services.IServicioService;
import com.oo2.grupo20.dto.EmpleadoDTO;
import com.oo2.grupo20.dto.ServicioConDiaDTO;
import com.oo2.grupo20.dto.ServicioDTO;
import com.oo2.grupo20.dto.ServicioSinTurnoDiaDTO;

@Service("servicioService")
public class ServicioService implements IServicioService {
	
	private IServicioRepository servicioRepository;
	private IDiaRepository diaRepository;
	
	private ModelMapper modelMapper = new ModelMapper ();
	
	public ServicioService (IServicioRepository servicioRepository, IDiaRepository diaRepository) {
		this.servicioRepository = servicioRepository;
		this.diaRepository = diaRepository;
	}
	
	@Override
	public List<Servicio> getAll(){
		return servicioRepository.findAll();
	}
	
	@Override
	public Servicio insertOrUpdate(Servicio servicio) {
	    Servicio saved = servicioRepository.save(servicio);
	    generarDiasParaServicio(saved); // <- esto genera los objetos Dia en base a los días disponibles
	    return saved;
	}
	
	@Override
	public boolean remove(Long idServicio) {
		try {
			servicioRepository.deleteById(idServicio);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Optional<ServicioDTO> findByNombreServicio(String nombreServicio){
	    return servicioRepository.findByNombreServicio(nombreServicio)
	            .map(servicio -> ServicioDTO.toDTO(servicio));
	}
	
	@Override
	public Optional<ServicioDTO> findByDescripcion(String descripcion){
		return servicioRepository.findByDescripcion(descripcion)
				.map(servicio -> ServicioDTO.toDTO(servicio));
	}
	
	@Override
	public Optional<ServicioDTO> findByIdServicio(Long idServicio){
		return servicioRepository.findById(idServicio)
				.map(servicio ->ServicioDTO.toDTO(servicio));
	}
	
	@Override
	public Optional<ServicioDTO> findByIdServicioWithDias(Long idServicio){
		return servicioRepository.findServicioByIdWithDias(idServicio)
				.map(servicio -> ServicioDTO.toDTO(servicio));
	}
	
	@Override
	public Optional<ServicioConDiaDTO> findByIdServicioWithDias2(Long idServicio) {
	    return servicioRepository.findServicioByIdWithDiasAndEstablecimiento(idServicio)
	        .map(servicio -> {
	            // DEBUG: Imprimimos los días recuperados
	            System.out.println("Días encontrados:");
	            servicio.getDias().forEach(d -> System.out.println("Fecha: " + d.getFecha()));

	            // Mapeamos con ModelMapper
	            return modelMapper.map(servicio, ServicioConDiaDTO.class);
	        });
	}

	
	@Override
	public void generarDiasParaServicio(Servicio servicio) {
	    LocalDate hoy = LocalDate.now();
	    LocalDate fin = hoy.plusDays(365); //Creamos Días para un año.

	    for (LocalDate fecha = hoy; !fecha.isAfter(fin); fecha = fecha.plusDays(1)) {
	        if (servicio.getDiasDisponibles().contains(fecha.getDayOfWeek())) {
	            Dia dia = new Dia();
	            dia.setFecha(fecha);
	            dia.setServicio(servicio);
	            diaRepository.save(dia);
	        }
	    }
	}

	

	@Override
	public Optional<ServicioDTO> findByIdServicioWithDiasAndTurnos(Long id) {
	    return servicioRepository.findByIdServicioWithDiasAndTurnos(id)
	            .map(servicio -> ServicioDTO.toDTO(servicio));
	}
	
	
	@Override
	public Optional<ServicioDTO> findByIdWithEstablecimiento(Long id) {
	    return servicioRepository.findByIdWithEstablecimiento(id)
	            .map(servicio -> ServicioDTO.toDTO(servicio));
	}
	
	@Override
	public Optional<ServicioSinTurnoDiaDTO> findByIdWithEstablecimiento2(Long id) {
	    return servicioRepository.findByIdWithEstablecimiento(id)
	            .map(servicio -> modelMapper.map(servicio, ServicioSinTurnoDiaDTO.class));
	}
	
	
	@Override
	public Optional<ServicioDTO> findByIdServicioWithEstablecimientoAndEmpleados(Long id) {
	    return servicioRepository.findByIdWithEstablecimientoAndEmpleados(id)
	        .map(servicio -> ServicioDTO.toDTO(servicio));
	}


	

	public Servicio getServicioEntityById(Long id) {
	    return servicioRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Servicio no encontrado con id: " + id));
	}



}
