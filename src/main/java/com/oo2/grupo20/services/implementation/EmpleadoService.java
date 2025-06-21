package com.oo2.grupo20.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oo2.grupo20.entities.Cliente;
import com.oo2.grupo20.entities.Empleado;
import com.oo2.grupo20.entities.Rol;
import com.oo2.grupo20.exceptions.EmpleadoTieneTurnoException;
import com.oo2.grupo20.dto.EmpleadoConEspecialidadesYEstablecimientoDTO;
import com.oo2.grupo20.dto.EmpleadoDTO;
import com.oo2.grupo20.services.IEmpleadoService;
import com.oo2.grupo20.repositories.IEmpleadoRepository;
import com.oo2.grupo20.repositories.ITurnoRepository;



@Service ("empleadoService")
public class EmpleadoService implements IEmpleadoService {
	
	private IEmpleadoRepository empleadoRepository;
	private final PasswordEncoder passwordEncoder;
	private final ITurnoRepository turnoRepository;
	
	private ModelMapper modelMapper = new ModelMapper ();
	
	public EmpleadoService(IEmpleadoRepository empleadoRepository, PasswordEncoder passwordEncoder, ITurnoRepository turnoRepository) {
	    this.empleadoRepository = empleadoRepository;
	    this.passwordEncoder = passwordEncoder;
	    this.turnoRepository = turnoRepository;
	}

	
	@Override
	public List<Empleado> getAll(){
		
		return empleadoRepository.findAll();
	}
	
	@Override
	public Empleado insertOrUpdate(Empleado empleado) {
	    // Si es nuevo, codificamos la contraseña
	    if (empleado.getId() == null) {
	        empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
	    }

	    empleado.setRol(Rol.EMPLEADO); // Asignar rol
	    empleado.setEstado(true);
	    return empleadoRepository.save(empleado);
	}
	
	@Override
	public Empleado findByEmail(String email) {
	    return empleadoRepository.findByEmail(email)
	        .orElseThrow(() -> new RuntimeException("Empleado no encontrado con email: " + email));
	}



	@Override
	public List<Empleado> findByEstablecimientoId(Long idEstablecimiento) {
	    return empleadoRepository.findByEstablecimientoId(idEstablecimiento);
	}

	
	@Override
	public boolean remove(long idEmpleado) {
		if (turnoRepository.existsByEmpleadoId(idEmpleado)) {
		    throw new EmpleadoTieneTurnoException("No se puede eliminar el empleado porque tiene turnos asignados.");
		}

	    try {
	        empleadoRepository.deleteById(idEmpleado);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	
	
	@Override
	public Empleado findById(Long id) {
	    return empleadoRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + id));
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
	
	/*@Override
	public Optional<EmpleadoDTO> findByIdWithEspecialidades(Long id) {
	    return empleadoRepository.findByIdWithEspecialidades(id)
	            .map(empleado -> modelMapper.map(empleado, EmpleadoDTO.class));
	}*/
	
	@Override
	public Optional<EmpleadoDTO> findByIdWithEspecialidadesAndEstablecimiento(Long id) {
	    return empleadoRepository.findByIdWithEspecialidadesAndEstablecimiento(id)
	            .map(empleado -> modelMapper.map(empleado, EmpleadoDTO.class));
	}
	
    public Optional<EmpleadoConEspecialidadesYEstablecimientoDTO> findByIdWithEspecialidadesAndEstablecimiento2(Long id){
    	
    	 return empleadoRepository.findByIdWithEspecialidadesAndEstablecimiento2(id)
 	            .map(empleado -> modelMapper.map(empleado, EmpleadoConEspecialidadesYEstablecimientoDTO.class));
 	
    }


    @Override
    public Empleado getEmpleadoEntityById(Long id) {
        return empleadoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + id));
    }
	
	
	
}
