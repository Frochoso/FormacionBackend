package block7crudvalidation.block7_crud_validation.dtos.asignatura;

import block7crudvalidation.block7_crud_validation.dtos.student.StudentDtoPost;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaDtoPost {

    String nombreAsignatura;

    String comments;

    LocalDate initialDate;

    LocalDate finishDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsignaturaDtoPost that = (AsignaturaDtoPost) o;
        return Objects.equals(nombreAsignatura, that.nombreAsignatura) && Objects.equals(comments, that.comments) && Objects.equals(initialDate, that.initialDate) && Objects.equals(finishDate, that.finishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreAsignatura, comments, initialDate, finishDate);
    }
}
