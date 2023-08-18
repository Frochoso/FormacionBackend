package block7crudvalidation.block7_crud_validation.dtos.persona;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDtoGet {

    Integer idPersona;

    String usuario;

    String password;

    String name;

    String surname;

    String companyEmail;

    String personalEmail;

    String city;

    boolean active;

    LocalDate createdDate;

    String imagenUrl;

    LocalDate terminationDate;

}
