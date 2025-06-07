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
	/*	@GetMapping("/index")
	    public ModelAndView index() {
	        ModelAndView mAV = new ModelAndView(ViewRouteHelper.EMPLEADO_INDEX);
	        mAV.addObject("empleados", empleadoService.getAll());
	        return mAV;
	    } */
		
		// Vista del sistema de turnos con Bootstrap
		
	  /*  @GetMapping("/indexBootstrap")
	    public ModelAndView indexBootstrap() {
	        ModelAndView modelAndView = new ModelAndView("empleado/indexBootstrap");
	        
	        List<EmpleadoDTO> empleadosDisponibles = empleadoService.getAll()
	            .stream()
	            .map(e -> modelMapper.map(e, EmpleadoDTO.class))
	            .collect(Collectors.toList());
	        
	        modelAndView.addObject("empleadosDisponibles", empleadosDisponibles);
	        return modelAndView;
	    }*/
		
		
		
		
		@GetMapping("/index")
	    public ModelAndView index() {
	        ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.EMPLEADO_INDEX);
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
	        return new RedirectView("/empleado/index");
	    }
		
		// NUEVOS MVC
	    
	    @GetMapping("/{id}")
	    public ModelAndView get(@PathVariable("id") long id) {
	        ModelAndView mAV = new ModelAndView(ViewRouteHelper.EMPLEADO_UPDATE);
	        EmpleadoDTO empleadoDTO = modelMapper.map(empleadoService.getAll().stream()
	                .filter(e -> e.getId() == id)
	                .findFirst()
	                .orElse(null), EmpleadoDTO.class);
	        mAV.addObject("empleado", empleadoDTO);
	        return mAV;
	    }

	    @GetMapping("/by_nombre/{nombre}")
	    public ModelAndView getByNombre(@PathVariable("nombre") String nombre) {
	        ModelAndView mAV = new ModelAndView(ViewRouteHelper.EMPLEADO_UPDATE);
	        empleadoService.findByNombre(nombre).ifPresent(empleadoDTO -> 
	            mAV.addObject("empleado", empleadoDTO));
	        return mAV;
	    }

	    @GetMapping("/by_apellido/{apellido}")
	    public ModelAndView getByApellido(@PathVariable("apellido") String apellido) {
	        ModelAndView mAV = new ModelAndView(ViewRouteHelper.EMPLEADO_UPDATE);
	        empleadoService.findByApellido(apellido).ifPresent(empleadoDTO -> 
	            mAV.addObject("empleado", empleadoDTO));
	        return mAV;
	    }

	    @GetMapping("/by_cuil/{cuil}")
	    public ModelAndView getByCuilWithEspecialidades(@PathVariable("cuil") String cuil) {
	        ModelAndView mAV = new ModelAndView(ViewRouteHelper.EMPLEADO_DETAIL);
	        empleadoService.findByCUILWithEspecialidades(cuil).ifPresent(empleadoDTO -> 
	            mAV.addObject("empleado", empleadoDTO));
	        return mAV;
	    }

	    @GetMapping("/detail/{id}")
	    public ModelAndView getByIdWithEspecialidades(@PathVariable("id") Long id) {
	        ModelAndView mAV = new ModelAndView(ViewRouteHelper.EMPLEADO_DETAIL);
	        empleadoService.findByIdWithEspecialidadesAndEstablecimiento(id).ifPresent(empleadoDTO -> 
	            mAV.addObject("empleado", empleadoDTO));
	        return mAV;
	    }


	    
	    @PostMapping("/update")
	    public RedirectView update(@ModelAttribute("empleado") EmpleadoDTO empleadoDTO) {
	        Empleado empleado = modelMapper.map(empleadoDTO, Empleado.class);
	        empleadoService.insertOrUpdate(empleado);
	        return new RedirectView(ViewRouteHelper.EMPLEADO_ROOT);
	    }

	    @PostMapping("/delete/{id}")
	    public RedirectView delete(@PathVariable("id") long id) {
	        empleadoService.remove(id);
	        return new RedirectView(ViewRouteHelper.EMPLEADO_ROOT);
	    }
		

		
}
