package com.oo2.grupo20.services;

import java.util.List;
import java.util.Optional;

import com.oo2.grupo20.dto.ClienteDTO;
import com.oo2.grupo20.entities.Cliente;

public interface IClienteService {

	public List<Cliente> getAll();
	
	public Cliente insertOrUpdate(Cliente cliente);
	
	public boolean remove(long idCliente);
	    
	public Optional<ClienteDTO> findByDni(Integer dni);
	
	public List<ClienteDTO> findByApellido(String apellido);
	
	public List<ClienteDTO> findByNombreAndApellido(String nombre, String apellido);
	    
//	public List<ClienteDTO> findByFechaRegistroBetween(LocalDate inicio, LocalDate fin);
	
//	public List<ClienteDTO> findClientesConTurnoEnFecha(LocalDate fecha);
	    
	public boolean existsByDni(Integer dni);
	
	public boolean existsByEmail(String email);

	public ClienteDTO getById(long id);

	public Cliente getClienteEntityById(long id);
	    
	
}
