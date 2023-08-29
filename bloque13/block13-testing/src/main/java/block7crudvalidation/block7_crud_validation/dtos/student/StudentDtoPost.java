package block7crudvalidation.block7_crud_validation.dtos.student;

import block7crudvalidation.block7_crud_validation.enumerations.Branch;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoPost {

    Integer numHoursWeek;

    String comments;

    Integer idProfessor;

    Integer idPersona;

    Set<Integer> asignaturas;

    Branch branch;
}
