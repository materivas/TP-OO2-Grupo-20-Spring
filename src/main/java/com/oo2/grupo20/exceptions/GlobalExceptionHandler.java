package com.oo2.grupo20.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


import com.oo2.grupo20.exceptions.EstablecimientoConEmpleadosException;
import com.oo2.grupo20.exceptions.EstablecimientoNoEncontradoException;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejo genérico
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneral(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Error interno: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    // Manejo específico para EntityNotFound
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(EntityNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Errores de validación de @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
    
    
    @ExceptionHandler(EstablecimientoConEmpleadosException.class)
    public String handleEstablecimientoConEmpleados(EstablecimientoConEmpleadosException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/establecimiento-con-empleados";
    }

    @ExceptionHandler(EstablecimientoNoEncontradoException.class)
    public String handleEstablecimientoNoEncontrado(EstablecimientoNoEncontradoException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/establecimiento-no-encontrado";
    }
    
    @ExceptionHandler(EspecialidadesConEmpleadosException.class)
    public String handleEspecialidadesConEmpleados(EspecialidadesConEmpleadosException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/especialidad-con-empleados";
    }
    
    @ExceptionHandler(EstablecimientoConServiciosException.class)
    public String handleEstablecimientoConServicios(EstablecimientoConServiciosException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/establecimiento-con-servicios";
    }
    
    @ExceptionHandler(EmpleadoTieneTurnoException.class)
    public String handleEmpleadoTieneTurno(EmpleadoTieneTurnoException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/empleado-con-turnos";
    }
    
    @ExceptionHandler(EmailDuplicadoException.class)
    public String handleEmailDuplicado(EmailDuplicadoException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/email-duplicado"; // 
    }




 // Error 404 - Ruta no encontrada
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException ex, Model model) {
        model.addAttribute("errorMessage", "La página que estás buscando no fue encontrada.");
        return "error/404";
    }

    // Error 500 - Error interno del servidor
    @ExceptionHandler(RuntimeException.class)
    public String handle500(RuntimeException ex, Model model) {
        model.addAttribute("errorMessage", "Ocurrió un error interno en el servidor.");
        return "error/500";
    }

    
    
    
}

