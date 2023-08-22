package block7crudvalidation.block7_crud_validation.dtos.professor;

import block7crudvalidation.block7_crud_validation.enumerations.Branch;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class FullProfessorDtoGet extends ProfessorDtoGet {

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

    public FullProfessorDtoGet(Integer idProfessor, Integer idPersona, String comments, Branch branch, String usuario,
                               String password, String name, String surname, String companyEmail, String personalEmail,
                               String city, boolean active, LocalDate createdDate, String imagenUrl,
                               LocalDate terminationDate) {

        super(idProfessor, idPersona, comments, branch);
        this.usuario = usuario;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.companyEmail = companyEmail;
        this.personalEmail = personalEmail;
        this.city = city;
        this.active = active;
        this.createdDate = createdDate;
        this.imagenUrl = imagenUrl;
        this.terminationDate = terminationDate;

    }
}
