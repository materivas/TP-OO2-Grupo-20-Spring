package com.oo2.grupo20.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo20.entities.Especialidad;
import com.oo2.grupo20.dto.EspecialidadDTO;
import com.oo2.grupo20.services.IEspecialidadService;
import com.oo2.grupo20.repositories.IEspecialidadRepository;



@Service ("especialidadService")
public class EspecialidadService implements IEspecialidadService {
	
	private IEspecialidadRepository especialidadRepository;
	
	private ModelMapper modelMapper = new ModelMapper ();
	
	public EspecialidadService (IEspecialidadRepository especialidadRepository) {
		this.especialidadRepository = especialidadRepository;
	}

	
	
	//Trae todos los atributos de Especialidad (Para vista de un admin o alguien con autorización por ejemplo)
	@Override
	public List<Especialidad> getAllFull(){
		
		return especialidadRepository.findAll();
	}
	
	
	//Trae  solo la entidad acotada (Vista de usuario por ejemplo)
	@Override
	public List<EspecialidadDTO> getAllPublic() {
	    return especialidadRepository.findAll().stream()
	        .map(establecimiento -> modelMapper.map(establecimiento, EspecialidadDTO.class)) // transforma la entidad completa al DTO
	        .collect(Collectors.toList());
	}

	
	@Override
	public Especialidad insertOrUpdate (Especialidad especialidad) {
		return especialidadRepository.save(especialidad);
	}
	
	
	@Override
	public boolean remove (long idEspecialidad) {
		try {
			especialidadRepository.deleteById(idEspecialidad);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Optional<Especialidad> findByIdEspecialidad(long idEspecialidad) {
	    return especialidadRepository.findByIdEspecialidad(idEspecialidad);

	}

	@Override
	public Optional<Especialidad> findByNombre(String nombre) {
	    return especialidadRepository.findByNombre(nombre);
	}

	@Override
	public Optional<Especialidad> findByIdWithEmpleados(long idEspecialidad) {
	    return especialidadRepository.findByIdWithEmpleados(idEspecialidad);
	}
	
	
	
}
