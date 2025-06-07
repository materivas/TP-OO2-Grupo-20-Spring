package com.oo2.grupo20.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.oo2.grupo20.dto.DiaDTO;
import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.helpers.ViewRouteHelper;
import com.oo2.grupo20.services.IDiaService;
import com.oo2.grupo20.helpers.ViewRouteHelper;

import java.time.LocalDate;

@Controller
@RequestMapping("/dia")
public class DiaController {

    private final IDiaService diaService;
    private final ModelMapper modelMapper = new ModelMapper();

    public DiaController(IDiaService diaService) {
        this.diaService = diaService;
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
        return mAV;
    }

    @PostMapping("/create")
    public RedirectView create(@ModelAttribute("dia") DiaDTO diaDTO) {
        Dia dia = modelMapper.map(diaDTO, Dia.class);
        diaService.insertOrUpdate(dia);
        return new RedirectView(ViewRouteHelper.DIA_ROOT);
    }

    @GetMapping("/{id:\\d+}")
    public ModelAndView get(@PathVariable("id") long id) {

        ModelAndView mAV = new ModelAndView(ViewRouteHelper.DIA_UPDATE);
        diaService.findByIdDia(id).ifPresent(dia ->
            mAV.addObject("dia", modelMapper.map(dia, DiaDTO.class)));
        return mAV;
    }

    @PostMapping("/update")
    public RedirectView update(@ModelAttribute("dia") DiaDTO diaDTO) {
        Dia dia = modelMapper.map(diaDTO, Dia.class);
        diaService.insertOrUpdate(dia);
        return new RedirectView(ViewRouteHelper.DIA_ROOT);
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable("id") long id) {
        diaService.remove(id);
        return new RedirectView(ViewRouteHelper.DIA_ROOT);
    }

    //Traer un día por fecha
    @GetMapping("/by_fecha/{fecha}")
    public ModelAndView getByFecha(@PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.DIA_UPDATE);
        diaService.findByFecha(fecha).ifPresent(dia ->
            mAV.addObject("dia", modelMapper.map(dia, DiaDTO.class)));
        return mAV;
    }

    //Traer un día con sus turnos
    @GetMapping("/with_turnos/{id}")
    public ModelAndView getWithTurnos(@PathVariable("id") long id) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.DIA_DETAIL);
        diaService.findDiaByIdWithTurnos(id).ifPresent(dia ->
            mAV.addObject("dia", modelMapper.map(dia, DiaDTO.class)));
        return mAV;
    }
}

