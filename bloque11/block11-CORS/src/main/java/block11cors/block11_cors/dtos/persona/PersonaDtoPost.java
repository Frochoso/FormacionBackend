package block11cors.block11_cors.dtos.persona;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDtoPost {

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
