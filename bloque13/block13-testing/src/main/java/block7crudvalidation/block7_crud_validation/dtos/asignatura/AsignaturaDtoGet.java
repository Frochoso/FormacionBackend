package block7crudvalidation.block7_crud_validation.dtos.asignatura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaDtoGet {

    Integer idAsignatura;

    String nombreAsignatura;

    String comments;

    LocalDate initialDate;

    LocalDate finishDate;

    public AsignaturaDtoGet(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsignaturaDtoGet that = (AsignaturaDtoGet) o;
        return Objects.equals(idAsignatura, that.idAsignatura) && Objects.equals(nombreAsignatura, that.nombreAsignatura) && Objects.equals(comments, that.comments) && Objects.equals(initialDate, that.initialDate) && Objects.equals(finishDate, that.finishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAsignatura, nombreAsignatura, comments, initialDate, finishDate);
    }
}
