package block11cors.block11_cors.dtos.student;

import block11cors.block11_cors.enumerations.Branch;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter

public class FullStudentDtoGet extends StudentDtoGet {

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

    public FullStudentDtoGet(Integer idStudent, Integer id_persona, Integer numHoursWeek, String comments,
                             Integer id_professor, Branch branch, Set<Integer> asignaturas, String usuario,
                             String password, String name, String surname, String companyEmail, String personalEmail,
                             String city, boolean active, LocalDate createdDate, String imagenUrl, LocalDate terminationDate) {
        
        super(idStudent, id_persona, numHoursWeek, comments, id_professor, branch, asignaturas);
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
