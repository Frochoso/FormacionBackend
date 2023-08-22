package block11cors.block11_cors.dtos.student;

import block11cors.block11_cors.enumerations.Branch;
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
