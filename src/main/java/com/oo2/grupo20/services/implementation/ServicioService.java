package com.oo2.grupo20.services.implementation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.repositories.IServicioRepository;
import com.oo2.grupo20.services.IServicioService;
import com.oo2.grupo20.dto.ServicioDTO;
import com.oo2.grupo20.dto.ServicioSinTurnoDiaDTO;

@Service("servicioService")
public class ServicioService implements IServicioService {
	
	private IServicioRepository servicioRepository;
	
	private ModelMapper modelMapper = new ModelMapper ();
	
	public ServicioService (IServicioRepository servicioRepository) {
		this.servicioRepository = servicioRepository;
	}
	
	@Override
	public List<Servicio> getAll(){
		return servicioRepository.findAll();
	}
	
	@Override
	public Servicio insertOrUpdate(Servicio servicio) {
		return servicioRepository.save(servicio);
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
