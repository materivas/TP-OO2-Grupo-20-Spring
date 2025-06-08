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

import com.oo2.grupo20.entities.Establecimiento;
import com.oo2.grupo20.helpers.ViewRouteHelper;
import com.oo2.grupo20.dto.EstablecimientoDTO;
import com.oo2.grupo20.services.IEstablecimientoService;
import com.oo2.grupo20.services.IServicioService;

@Controller
@RequestMapping("/establecimiento")
public class EstablecimientoController {

    private final IEstablecimientoService establecimientoService;
    private final IServicioService servicioService;
    private final ModelMapper modelMapper = new ModelMapper();

    public EstablecimientoController(IEstablecimientoService establecimientoService, IServicioService servicioService) {
        this.establecimientoService = establecimientoService;
        this.servicioService = servicioService;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESTABLECIMIENTO_INDEX);
        mAV.addObject("establecimientos", establecimientoService.getAllPublic());
        return mAV;
    }

    @GetMapping("/new")
    public ModelAndView create() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESTABLECIMIENTO_NEW);
        mAV.addObject("establecimiento", new EstablecimientoDTO());
        mAV.addObject("servicios", servicioService.getAll());
        return mAV;
    }

    @PostMapping("/create")
    public RedirectView create(@ModelAttribute("establecimiento") EstablecimientoDTO establecimientoDTO) {
        Establecimiento establecimiento = modelMapper.map(establecimientoDTO, Establecimiento.class);
        establecimientoService.insertOrUpdate(establecimiento);
        return new RedirectView(ViewRouteHelper.ESTABLECIMIENTO_ROOT);
    }

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable("id") Long id) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESTABLECIMIENTO_UPDATE);
        establecimientoService.findByIdEstablecimiento(id).ifPresent(establecimiento -> 
            mAV.addObject("establecimiento", modelMapper.map(establecimiento, EstablecimientoDTO.class)));
        return mAV;
    }

    @GetMapping("/by_nombre/{nombre}")
    public ModelAndView getByNombre(@PathVariable("nombre") String nombre) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESTABLECIMIENTO_UPDATE);
        establecimientoService.findByNombre(nombre).ifPresent(establecimiento -> 
            mAV.addObject("establecimiento", modelMapper.map(establecimiento, EstablecimientoDTO.class)));
        return mAV;
    }

    @GetMapping("/with_empleados/{id}")
    public ModelAndView getWithEmpleados(@PathVariable("id") Long id) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESTABLECIMIENTO_DETAIL);
        establecimientoService.findByIdWithEmpleados(id).ifPresent(establecimiento -> 
            mAV.addObject("establecimiento", modelMapper.map(establecimiento, EstablecimientoDTO.class)));
        return mAV;
    }
    
    @GetMapping("/with_servicios/{id}")
    public ModelAndView getWithServicios(@PathVariable("id") Long id) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESTABLECIMIENTO_DETAIL_SERVICIOS);
        establecimientoService.findByIdWithServicios(id).ifPresent(establecimiento -> 
            mAV.addObject("establecimiento", modelMapper.map(establecimiento, EstablecimientoDTO.class)));
        return mAV;
    }

    @PostMapping("/update")
    public RedirectView update(@ModelAttribute("establecimiento") EstablecimientoDTO establecimientoDTO) {
        Establecimiento establecimiento = modelMapper.map(establecimientoDTO, Establecimiento.class);
        establecimientoService.insertOrUpdate(establecimiento);
        return new RedirectView(ViewRouteHelper.ESTABLECIMIENTO_ROOT);
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable("id") Long id) {
        establecimientoService.remove(id);
        return new RedirectView(ViewRouteHelper.ESTABLECIMIENTO_ROOT);
    }
}