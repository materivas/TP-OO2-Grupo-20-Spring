package com.oo2.grupo20.controllers;

import com.oo2.grupo20.entities.Turno;
import com.oo2.grupo20.services.ITurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.oo2.grupo20.helpers.ViewRouteHelper.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/turnos")
public class TurnoController {

    private final ITurnoService turnoService;

    @GetMapping
    public String listarTurnos(Model model) {
        List<Turno> turnos = turnoService.findAll(); 
        model.addAttribute("turnos", turnos);
        return TURNO_INDEX;
    }

    @GetMapping("/cliente/{dni}")
    public String turnosPorCliente(@PathVariable("dni") String dni, Model model) {
        List<Turno> turnos = turnoService.findByClienteDni(dni);
        model.addAttribute("turnos", turnos);
        return TURNO_INDEX;
    }

    @GetMapping("/empleado/{id}")
    public String turnosPorEmpleado(@PathVariable("id") Long id, Model model) {
        List<Turno> turnos = turnoService.findByEmpleadoId(id);
        model.addAttribute("turnos", turnos);
        return TURNO_INDEX;
    }

    //Muestra el form para un nuevo turno
    @GetMapping("/nuevo")
    public String nuevoTurno(Model model) {
        model.addAttribute("turno", new Turno());
        return TURNO_FORM;
    }

    //Guarda el turno desde el form
    @PostMapping("/guardar")
    public String guardarTurno(@ModelAttribute("turno") Turno turno) {
        turnoService.save(turno);
        return "redirect:/turnos";
    }
}
