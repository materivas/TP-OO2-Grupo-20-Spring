package com.oo2.grupo20.services.implementation;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo20.entities.Empleado;
import com.oo2.grupo20.entities.Especialidad;
import com.oo2.grupo20.entities.Establecimiento;
import com.oo2.grupo20.dto.EspecialidadDTO;
import com.oo2.grupo20.dto.EstablecimientoDTO;
import com.oo2.grupo20.services.IEstablecimientoService;
import com.oo2.grupo20.repositories.IEmpleadoRepository;
import com.oo2.grupo20.repositories.IEstablecimientoRepository;
import com.oo2.grupo20.exceptions.EstablecimientoConEmpleadosException;
import com.oo2.grupo20.exceptions.EstablecimientoConServiciosException;

@Service ("establecimientoService")
public class EstablecimientoService implements IEstablecimientoService{
	
	
private IEstablecimientoRepository establecimientoRepository;
	
	private ModelMapper modelMapper = new ModelMapper ();
	
	public EstablecimientoService (IEstablecimientoRepository establecimientoRepository) {
		this.establecimientoRepository = establecimientoRepository;
	}

	
	//Trae la entidad completa (Para una vista de admin por ejemplo)
	@Override
	public List<Establecimiento> getAllFull(){
		
		return establecimientoRepository.findAll();
	}
	
	//Trae  solo la entidad acotada (Vista de usuario por ejemplo)
		@Override
	public List<EstablecimientoDTO> getAllPublic() {
		return establecimientoRepository.findAll().stream()
		       .map(establecimiento -> modelMapper.map(establecimiento, EstablecimientoDTO.class)) // transforma la entidad completa al DTO
		       .collect(Collectors.toList());
		}
	
	
	
	@Override
	public Establecimiento insertOrUpdate (Establecimiento establecimiento) {
		return establecimientoRepository.save(establecimiento);
	}
	
	
	@Override
	public boolean remove(long idEstablecimiento) {
	    Optional<Establecimiento> optionalEstablecimiento = establecimientoRepository.findByIdWithEmpleados(idEstablecimiento);

	    if (optionalEstablecimiento.isPresent()) {
	        Establecimiento est = optionalEstablecimiento.get();

	        if (!est.getEmpleados().isEmpty()) {
	            throw new EstablecimientoConEmpleadosException("No se puede eliminar un establecimiento con empleados asociados.");
	        }

	        if (est.getServicios() != null && !est.getServicios().isEmpty()) {
	            throw new EstablecimientoConServiciosException("No se puede eliminar un establecimiento con servicios asociados.");
	        }

	        establecimientoRepository.deleteById(idEstablecimiento);
	        return true;
	    }

	    return false;
	}

	
	
	@Override
	public Optional<Establecimiento> findByIdEstablecimiento(long idEstablecimiento) {
	    return establecimientoRepository.findByIdEstablecimiento(idEstablecimiento);

	}

	@Override
	public Optional<Establecimiento> findByNombre(String nombre) {
	    return establecimientoRepository.findByNombre(nombre);
	}

	@Override
	public Optional<Establecimiento> findByIdWithEmpleados(long idEstablecimiento) {
	    return establecimientoRepository.findByIdWithEmpleados(idEstablecimiento);
	}
	
	@Override
	public Optional<Establecimiento> findByIdWithServicios(long idEstablecimiento) {
	    return establecimientoRepository.findByIdWithServicios(idEstablecimiento);
	}
	
	@Override
    public Establecimiento getEstablecimientoEntityById(Long id) {
        return establecimientoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Establecimiento no encontrado con ID: " + id));
    }
	
	

}
