package com.oo2.grupo20.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo20.entities.Empleado;
import com.oo2.grupo20.dto.EmpleadoDTO;
import com.oo2.grupo20.services.IEmpleadoService;
import com.oo2.grupo20.repositories.IEmpleadoRepository;



@Service ("empleadoService")
public class EmpleadoService implements IEmpleadoService {
	
	private IEmpleadoRepository empleadoRepository;
	
	private ModelMapper modelMapper = new ModelMapper ();
	
	public EmpleadoService (IEmpleadoRepository empleadoRepository) {
		this.empleadoRepository = empleadoRepository;
	}

	
	@Override
	public List<Empleado> getAll(){
		
		return empleadoRepository.findAll();
	}
	
	@Override
	public Empleado insertOrUpdate (Empleado empleado) {
		return empleadoRepository.save(empleado);
	}
	
	
	@Override
	public boolean remove (long idEmpleado) {
		try {
			empleadoRepository.deleteById(idEmpleado);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Optional<EmpleadoDTO> findByNombre(String nombre) {
	    return empleadoRepository.findByNombre(nombre)
	            .map(empleado -> modelMapper.map(empleado, EmpleadoDTO.class));
	}

	@Override
	public Optional<EmpleadoDTO> findByApellido(String apellido) {
	    return empleadoRepository.findByApellido(apellido)
	            .map(empleado -> modelMapper.map(empleado, EmpleadoDTO.class));
	}

	@Override
	public Optional<EmpleadoDTO> findByCUILWithEspecialidades(String cuil) {
	    return empleadoRepository.findByCUILWithEspecialidades(cuil)
	            .map(empleado -> modelMapper.map(empleado, EmpleadoDTO.class));
	}
	
	
	
}
