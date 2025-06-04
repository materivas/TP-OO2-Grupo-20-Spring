package com.oo2.grupo20.services;



import java.util.*;


import com.oo2.grupo20.entities.Establecimiento;
import com.oo2.grupo20.dto.EstablecimientoDTO;



public interface IEstablecimientoService {
	
	
	public List<Establecimiento> getAllFull();
	
	public List<EstablecimientoDTO> getAllPublic();
	
	public Establecimiento insertOrUpdate(Establecimiento establecimiento);

    public boolean remove(long idEstablecimiento);
    
    public Optional <Establecimiento> findByIdEstablecimiento (long idEstablecimiento);
    
    public Optional <Establecimiento> findByNombre (String nombre);

    public Optional <Establecimiento> findByIdWithEmpleados (long idEstablecimiento);

    
    
	

}
