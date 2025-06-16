package com.oo2.grupo20.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.oo2.grupo20.dto.DiaDTO;
import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.services.IDiaService;
import com.oo2.grupo20.services.IServicioService;
import com.oo2.grupo20.helpers.ViewRouteHelper;

import java.time.LocalDate;

@Controller
@RequestMapping("/dia")
public class DiaController {

    private final IDiaService diaService;
    private final IServicioService servicioService;


    public DiaController(IDiaService diaService, IServicioService servicioService) {
        this.diaService = diaService;
        this.servicioService = servicioService;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.DIA_INDEX);
        mAV.addObject("dias", diaService.getAll());
        return mAV;
    }

    @GetMapping("/new")
    public ModelAndView create() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.DIA_NEW);
        mAV.addObject("dia", new DiaDTO());
        mAV.addObject("servicios", servicioService.getAll()); // Carga los servicios
        return mAV;
    }

    @PostMapping("/create")
    public RedirectView create(@ModelAttribute("dia") DiaDTO diaDTO) {
        Dia dia = diaDTO.fromDTO(diaDTO);
        diaService.insertOrUpdate(dia);
        return new RedirectView(ViewRouteHelper.DIA_ROOT);
    }


    
    

    @GetMapping("/{id:\\d+}")
    public ModelAndView get(@PathVariable("id") long id) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.DIA_UPDATE);
        diaService.findByIdDia(id).ifPresent(dia ->
            mAV.addObject("dia", dia)
        );
        mAV.addObject("servicios", servicioService.getAll()); // <--- ¡Esto es necesario!
        return mAV;
    }


    @PostMapping("/update")
    public RedirectView update(@ModelAttribute("dia") DiaDTO diaDTO) {
        Dia dia = diaDTO.fromDTO(diaDTO);
        diaService.insertOrUpdate(dia);
        return new RedirectView(ViewRouteHelper.DIA_ROOT);
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable("id") long id) {
        diaService.remove(id);
        return new RedirectView(ViewRouteHelper.DIA_ROOT);
    }

    @GetMapping("/by_fecha/{fecha}")
    public ModelAndView getByFecha(@PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.DIA_UPDATE);
        diaService.findByFecha(fecha).ifPresent(dia ->
            mAV.addObject("dia", dia));
        return mAV;
    }

    @GetMapping("/with_turnos/{id}")
    public ModelAndView getWithTurnos(@PathVariable("id") long id) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.DIA_DETAIL);
        diaService.findDiaByIdWithTurnos(id).ifPresent(dia ->
            mAV.addObject("dia", dia));
        return mAV;
    }
}





