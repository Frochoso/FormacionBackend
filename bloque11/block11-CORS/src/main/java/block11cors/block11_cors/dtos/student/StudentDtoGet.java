package block11cors.block11_cors.dtos.student;

import block11cors.block11_cors.enumerations.Branch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoGet {
    Integer idStudent;

    Integer id_persona;

    Integer numHoursWeek;

    String comments;

    Integer id_professor;

    Branch branch;

    Set<Integer> asignaturas;
}
