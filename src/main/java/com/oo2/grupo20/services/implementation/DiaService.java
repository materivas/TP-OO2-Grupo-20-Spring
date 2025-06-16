package com.oo2.grupo20.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.repositories.IDiaRepository;
import com.oo2.grupo20.dto.DiaDTO;
import com.oo2.grupo20.dto.ServicioDTO;
import com.oo2.grupo20.services.IDiaService;

import jakarta.transaction.Transactional;

@Service("diaService")
public class DiaService implements IDiaService {
	
	private IDiaRepository diaRepository;
	
	private ModelMapper modelMapper = new ModelMapper ();
	
	public DiaService (IDiaRepository diaRepository) {
		this.diaRepository = diaRepository;
	}
	
	@Override
	public List<Dia> getAll(){
		return diaRepository.findAll();
	}
	
	@Override
	public Dia insertOrUpdate(Dia dia) {
		return diaRepository.save(dia);
	}
	
	@Override
	public boolean remove(long idDia) {
		try {
			diaRepository.deleteById(idDia);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Optional<DiaDTO> findByIdDia(long idDia){
		return diaRepository.findById(idDia)
				.map(dia -> DiaDTO.toDTO(dia));
	}
	
	
	@Override
	public Optional<DiaDTO> findByFecha(LocalDate fecha){
	    return diaRepository.findByFecha(fecha)
	            .map(dia -> DiaDTO.toDTO(dia));
	}
 
	public Optional<DiaDTO> findDiaByIdWithTurnos(long idDia){
		return diaRepository.findDiaByIdWithTurnos(idDia)
				.map(dia -> DiaDTO.toDTO(dia));
		
	}
	
	public Optional<Dia> findByFechaAndServicio(LocalDate fecha, Servicio servicio) {
	    return diaRepository.findByFechaAndServicio(fecha, servicio);
	}
	
	public Dia findOrCreateByFechaAndServicio(LocalDate fecha, Servicio servicio) {
	    // Primero busca por fecha y servicio
	    Optional<Dia> diaExistente = diaRepository.findByFechaAndServicio(fecha, servicio);
	    
	    if (diaExistente.isPresent()) {
	        return diaExistente.get();
	    }
	    
	    // Si no existe, verifica si hay un día con la misma fecha pero diferente servicio
	    Optional<Dia> diaMismaFecha = diaRepository.findByFecha(fecha);
	    
	    if (diaMismaFecha.isPresent()) {
	        // Actualiza el servicio del día existente
	        Dia dia = diaMismaFecha.get();
	        dia.setServicio(servicio);
	        return diaRepository.save(dia);
	    }
	    
	    // Crea un nuevo día
	    Dia nuevoDia = new Dia();
	    nuevoDia.setFecha(fecha);
	    nuevoDia.setServicio(servicio);
	    return diaRepository.save(nuevoDia);
	}

	 @Override
	 @Transactional
	 public Dia findOrCreateByFecha(LocalDate fecha) {
	    //Buscar día existente
	    Optional<Dia> diaExistente = diaRepository.findByFecha(fecha);
	        
	    //Si existe, lo retorna
	    if (diaExistente.isPresent()) {
	          return diaExistente.get();
	    }
	        
	    //Si no existe, crear nuevo
	    Dia nuevoDia = new Dia();
	    nuevoDia.setFecha(fecha);
	        
	    // Guardar y retornar el nuevo día
	    return diaRepository.save(nuevoDia);
	 }

}
