package com.oo2.grupo20.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.repositories.IDiaRepository;
import com.oo2.grupo20.dto.DiaDTO;
import com.oo2.grupo20.services.IDiaService;

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
				.map(dia -> modelMapper.map(dia, DiaDTO.class));
	}
	
	
	@Override
	public Optional<DiaDTO> findByFecha(LocalDate fecha){
	    return diaRepository.findByFecha(fecha)
	            .map(dia -> modelMapper.map(dia, DiaDTO.class));
	}
 
	public Optional<DiaDTO> findDiaByIdWithTurnos(long idDia){
		return diaRepository.findDiaByIdWithTurnos(idDia)
				.map(dia -> modelMapper.map(dia, DiaDTO.class));
		
	}

}
