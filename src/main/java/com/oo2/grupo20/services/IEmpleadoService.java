package com.oo2.grupo20.services;

import java.util.*;
import com.oo2.grupo20.entities.Empleado;
import com.oo2.grupo20.dto.EmpleadoConEspecialidadesYEstablecimientoDTO;

import com.oo2.grupo20.dto.EmpleadoDTO;


public interface IEmpleadoService {
	
	public List<Empleado> getAll();

    public Empleado insertOrUpdate(Empleado empleado);

    public boolean remove(long idEmpleado);
    
    Empleado findByEmail(String email); // o findByDni(String dni) si usás DNI como username


    public Optional<EmpleadoDTO> findByNombre(String nombre);

    public Optional<EmpleadoDTO> findByApellido(String apellido);

    public Optional<EmpleadoDTO> findByCUILWithEspecialidades(String cuil);
    
    //public Optional<EmpleadoDTO> findByIdWithEspecialidades(Long id);
    
    public Optional<EmpleadoDTO> findByIdWithEspecialidadesAndEstablecimiento(Long id);
    
    public Optional<EmpleadoConEspecialidadesYEstablecimientoDTO> findByIdWithEspecialidadesAndEstablecimiento2(Long id);

    
}
