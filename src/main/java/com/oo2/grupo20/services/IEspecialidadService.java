package com.oo2.grupo20.services;


import java.util.*;
import com.oo2.grupo20.entities.Especialidad;
import com.oo2.grupo20.dto.EspecialidadDTO;

public interface IEspecialidadService {

	
	public List<Especialidad> getAllFull();
	
	public List<EspecialidadDTO> getAllPublic();
	
	public Optional <Especialidad> findByIdEspecialidad (long idEspecialidad);
	
	public Optional <Especialidad> findByNombre (String nombre);

	public Optional <Especialidad> findByIdWithEmpleados (long idEspecialidad);

    public Especialidad insertOrUpdate(Especialidad especialidad);

    public boolean remove(long idEmpleado);
	
	
}
