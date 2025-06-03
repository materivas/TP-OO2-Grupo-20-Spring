package dto.response;

import lombok.Data;

@Data
public class PersonaResumenResponse {
    private Long id;
    private String nombreCompleto;
    private Integer dni;
}