package com.oo2.grupo20.services.implementation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.repositories.IServicioRepository;
import com.oo2.grupo20.services.IServicioService;
import com.oo2.grupo20.dto.ServicioDto;

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
	public boolean remove(long idServicio) {
		try {
			servicioRepository.deleteById(idServicio);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Optional<ServicioDto> findByNombreServicio(String nombreServicio){
	    return servicioRepository.findByNombreServicio(nombreServicio)
	            .map(servicio -> modelMapper.map(servicio, ServicioDto.class));
	}
	
	@Override
	public Optional<ServicioDto> findByDescripcion(String descripcion){
		return servicioRepository.findByDescripcion(descripcion)
				.map(servicio -> modelMapper.map(servicio, ServicioDto.class));
	}
	
	

}
