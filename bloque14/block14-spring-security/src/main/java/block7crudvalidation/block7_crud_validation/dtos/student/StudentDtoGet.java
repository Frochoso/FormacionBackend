package block7crudvalidation.block7_crud_validation.dtos.student;

import block7crudvalidation.block7_crud_validation.dtos.asignatura.AsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.enumerations.Branch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDtoGet that = (StudentDtoGet) o;
        return Objects.equals(idStudent, that.idStudent) && Objects.equals(id_persona, that.id_persona) && Objects.equals(numHoursWeek, that.numHoursWeek) && Objects.equals(comments, that.comments) && Objects.equals(id_professor, that.id_professor) && branch == that.branch && Objects.equals(asignaturas, that.asignaturas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudent, id_persona, numHoursWeek, comments, id_professor, branch, asignaturas);
    }
}
