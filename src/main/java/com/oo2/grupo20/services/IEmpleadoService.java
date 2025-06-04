package com.oo2.grupo20.services;

import java.util.*;
import com.oo2.grupo20.entities.Empleado;
import com.oo2.grupo20.dto.EmpleadoDTO;


public interface IEmpleadoService {
	
	public List<Empleado> getAll();

    public Empleado insertOrUpdate(Empleado empleado);

    public boolean remove(long idEmpleado);

    public Optional<EmpleadoDTO> findByNombre(String nombre);

    public Optional<EmpleadoDTO> findByApellido(String apellido);

    public Optional<EmpleadoDTO> findByCUILWithEspecialidades(String cuil);
	

}
