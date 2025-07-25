package com.oo2.grupo20.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EmpleadoRestDTO(

    Long id,

    @NotBlank(message = "El nombre no puede estar vacío")
    String nombre,

    @NotBlank(message = "El apellido no puede estar vacío")
    String apellido,

    @NotNull(message = "El DNI no puede ser nulo")
    Integer dni,

    @NotBlank(message = "El CUIL no puede estar vacío")
    @Size(min = 11, max = 13, message = "El CUIL debe tener entre 11 y 13 caracteres")
    String cuil,

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe proporcionar un email válido")
    String email,

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    String username,

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 4, message = "La contraseña debe tener al menos 4 caracteres")
    String password,

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    LocalDate fechaDeNacimiento,

    boolean estado,

    @NotBlank(message = "El rol es obligatorio")
    String rol,

    Long establecimientoId,

    Long[] especialidadesIds

) {}
