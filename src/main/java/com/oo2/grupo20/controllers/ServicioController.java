package com.oo2.grupo20.controllers;

import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.oo2.grupo20.dto.ServicioDTO;
import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.helpers.ViewRouteHelper;
import com.oo2.grupo20.services.IEstablecimientoService;
import com.oo2.grupo20.services.IServicioService;

@Controller
@RequestMapping("/servicio")
public class ServicioController {
    
    private final IServicioService servicioService;
    private final IEstablecimientoService establecimientoService;
    private final ModelMapper modelMapper = new ModelMapper();
    
    public ServicioController(IServicioService servicioService, IEstablecimientoService establecimientoService) {
        this.servicioService = servicioService;
        this.establecimientoService = establecimientoService;
    }
    
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.SERVICIO_INDEX);
        mAV.addObject("servicios", servicioService.getAll());
        return mAV;
    }
    
    @GetMapping("/new")
    public ModelAndView create() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.SERVICIO_NEW);
        mAV.addObject("servicio", new ServicioDTO());
        mAV.addObject("establecimientos", establecimientoService.getAllFull());
        return mAV;
    }
    
    @PostMapping("/create")
    public RedirectView create(@ModelAttribute("servicio") ServicioDTO servicioDTO) {
        Servicio servicio = ServicioDTO.fromDTO(servicioDTO);
        servicioService.insertOrUpdate(servicio);
        return new RedirectView(ViewRouteHelper.SERVICIO_ROOT);
    }


    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable("id") long id) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.SERVICIO_UPDATE);
        servicioService.findByIdServicio(id).ifPresent(servicio ->
            mAV.addObject("servicio", servicio)
        );
        mAV.addObject("establecimientos", establecimientoService.getAllFull());
        return mAV;
    }
    
    /*    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable("id") long id) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.SERVICIO_UPDATE);
        servicioService.findByIdServicio(id).ifPresent(servicio ->
            mAV.addObject("servicio", servicio) 
        );
        mAV.addObject("establecimientos", establecimientoService.getAllFull());
        return mAV;
    } */

    @GetMapping("/by_nombre/{nombre}")
    public ModelAndView getByNombre(@PathVariable("nombre") String nombre) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.SERVICIO_UPDATE);
        servicioService.findByNombreServicio(nombre).ifPresent(servicio ->
            mAV.addObject("servicio",servicio)
        );
        mAV.addObject("establecimientos", establecimientoService.getAllFull());
        return mAV;
    }

    @GetMapping("with_dias/{id}")
    public ModelAndView getWithDias(@PathVariable("id") long id) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.SERVICIO_DETAIL);
        servicioService.findByIdServicioWithDias(id).ifPresent(servicio ->
            mAV.addObject("servicio", servicio)
        );
        mAV.addObject("establecimientos", establecimientoService.getAllFull());
        return mAV;
    }





    @PostMapping("/update")
    public RedirectView update(@ModelAttribute("servicio") ServicioDTO servicioDTO) {
        Servicio servicio = ServicioDTO.fromDTO(servicioDTO);
        servicioService.insertOrUpdate(servicio);
        return new RedirectView(ViewRouteHelper.SERVICIO_ROOT);
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable("id") long id) {
        servicioService.remove(id);
        return new RedirectView(ViewRouteHelper.SERVICIO_ROOT);
    }
    
    @GetMapping("/detail/{id}")
    public ModelAndView detalle(@PathVariable("id") long id) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.SERVICIO_DETAIL);
        servicioService.findByIdServicioWithDiasAndTurnos(id).ifPresent(servicio -> 
            mAV.addObject("servicio", servicio)
        );
        return mAV;
    }


}

