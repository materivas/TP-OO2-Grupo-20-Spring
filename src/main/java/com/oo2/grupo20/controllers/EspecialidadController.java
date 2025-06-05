package com.oo2.grupo20.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.oo2.grupo20.dto.EspecialidadDTO;
import com.oo2.grupo20.entities.Especialidad;
import com.oo2.grupo20.helpers.ViewRouteHelper;
import com.oo2.grupo20.services.IEspecialidadService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/especialidad")
public class EspecialidadController {

    private final IEspecialidadService especialidadService;
    private final ModelMapper modelMapper = new ModelMapper();

    public EspecialidadController(IEspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESPECIALIDAD_INDEX);
        // Traemos todas las especialidades acotadas para usuarios (como en establecimiento)
        List<EspecialidadDTO> especialidadesDTO = especialidadService.getAllPublic()
                .stream()
                .collect(Collectors.toList());
        mAV.addObject("especialidades", especialidadesDTO);
        return mAV;
    }

    @GetMapping("/new")
    public ModelAndView create() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESPECIALIDAD_NEW);
        mAV.addObject("especialidad", new EspecialidadDTO());
        return mAV;
    }

    @PostMapping("/create")
    public RedirectView create(@ModelAttribute("especialidad") EspecialidadDTO especialidadDTO) {
        Especialidad especialidad = modelMapper.map(especialidadDTO, Especialidad.class);
        especialidadService.insertOrUpdate(especialidad);
        return new RedirectView(ViewRouteHelper.ESPECIALIDAD_ROOT);
    }

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable("id") long id) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESPECIALIDAD_UPDATE);
        especialidadService.findByIdEspecialidad(id).ifPresent(especialidad -> 
            mAV.addObject("especialidad", modelMapper.map(especialidad, EspecialidadDTO.class)));
        return mAV;
    }

    @GetMapping("/by_nombre/{nombre}")
    public ModelAndView getByNombre(@PathVariable("nombre") String nombre) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESPECIALIDAD_UPDATE);
        especialidadService.findByNombre(nombre).ifPresent(especialidad -> 
            mAV.addObject("especialidad", modelMapper.map(especialidad, EspecialidadDTO.class)));
        return mAV;
    }

    @GetMapping("/with_empleados/{id}")
    public ModelAndView getWithEmpleados(@PathVariable("id") long id) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESPECIALIDAD_DETAIL);
        especialidadService.findByIdWithEmpleados(id).ifPresent(especialidad -> 
            mAV.addObject("especialidad", modelMapper.map(especialidad, EspecialidadDTO.class)));
        return mAV;
    }

    @PostMapping("/update")
    public RedirectView update(@ModelAttribute("especialidad") EspecialidadDTO especialidadDTO) {
        Especialidad especialidad = modelMapper.map(especialidadDTO, Especialidad.class);
        especialidadService.insertOrUpdate(especialidad);
        return new RedirectView(ViewRouteHelper.ESPECIALIDAD_ROOT);
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable("id") long id) {
        especialidadService.remove(id);
        return new RedirectView(ViewRouteHelper.ESPECIALIDAD_ROOT);
    }
}
