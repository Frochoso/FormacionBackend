package block7crudvalidation.block7_crud_validation.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Persona.findByUsuario",query = "SELECT p FROM Persona p WHERE p.usuario = :usuario")
public class Persona {

    @Id
    @GeneratedValue
    private Integer idPersona;

    private String usuario;

    private String password;

    private String name;

    private String surname;

    private String companyEmail;

    private String personalEmail;

    private String city;

    private boolean active;

    private LocalDate createdDate;

    private String imagenUrl;

    private LocalDate terminationDate;

}
