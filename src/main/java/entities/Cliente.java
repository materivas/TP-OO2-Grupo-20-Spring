package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Entity
@DiscriminatorValue("CLIENTE") // si usás herencia SINGLE_TABLE
public class Cliente extends Persona {
    
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy = "cliente")
    private List<Turno> turnos;

}
