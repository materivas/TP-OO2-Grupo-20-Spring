package com.oo2.grupo20.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.oo2.grupo20.entities.Empleado;
import com.oo2.grupo20.helpers.ViewRouteHelper;
import com.oo2.grupo20.dto.EmpleadoDTO;
import com.oo2.grupo20.services.IEmpleadoService;





@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

	
		private IEmpleadoService empleadoService;
		private final ModelMapper modelMapper = new ModelMapper();
	
		public EmpleadoController(IEmpleadoService empleadoService) {
			this.empleadoService = empleadoService;
		}
		
		//GET Example: SERVER/index
		@GetMapping("/index")
		public ModelAndView index() {
		    ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.EMPLEADO_INDEX);
		    return modelAndView;
		}
		
		
		@GetMapping("/index2")
	    public ModelAndView index2() {
	        ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.EMPLEADO_INDEX2);
	        List<EmpleadoDTO> empleadosDTO = empleadoService.getAll()
	                .stream()
	                .map(e -> modelMapper.map(e, EmpleadoDTO.class))
	                .collect(Collectors.toList());
	        modelAndView.addObject("empleados", empleadosDTO);
	        return modelAndView;
	    }

	    @GetMapping("/new")
	    public ModelAndView create() {
	        ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.EMPLEADO_NEW);
	        modelAndView.addObject("empleado", new EmpleadoDTO());
	        return modelAndView;
	    }

	    @PostMapping("/create")
	    public RedirectView create(@ModelAttribute("empleado") EmpleadoDTO empleadoDTO) {
	        Empleado empleado = modelMapper.map(empleadoDTO, Empleado.class);
	        empleadoService.insertOrUpdate(empleado);
	        return new RedirectView("/empleado/index2");
	    }
		
		
		

		
}
