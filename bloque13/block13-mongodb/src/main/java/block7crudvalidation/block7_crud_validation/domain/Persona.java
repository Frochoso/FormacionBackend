package block7crudvalidation.block7_crud_validation.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

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
