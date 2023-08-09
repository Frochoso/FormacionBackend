package block7_crud.block7_crud.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Persona.findByName", query = "SELECT p FROM Persona p WHERE p.nombre = :nombre ")
public class Persona {

    @Id
    @GeneratedValue
    private Integer id;

    private String nombre;

    private String edad;

    private String localidad;

}
