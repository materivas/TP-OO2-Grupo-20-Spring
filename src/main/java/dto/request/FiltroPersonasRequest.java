package dto.request;

import lombok.Data;

@Data
public class FiltroPersonasRequest {
    private String nombre;
    private String apellido;
    private Integer dni;
    private Boolean estado;
    private Integer anioNacimiento;
}