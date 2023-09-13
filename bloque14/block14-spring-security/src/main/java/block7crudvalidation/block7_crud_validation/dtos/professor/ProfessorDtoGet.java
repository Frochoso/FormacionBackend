package block7crudvalidation.block7_crud_validation.dtos.professor;

import block7crudvalidation.block7_crud_validation.enumerations.Branch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDtoGet {

    Integer idProfessor;

    Integer idPersona;

    String comments;

    Branch branch;

    public ProfessorDtoGet(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorDtoGet that = (ProfessorDtoGet) o;
        return Objects.equals(idProfessor, that.idProfessor) && Objects.equals(idPersona, that.idPersona) && Objects.equals(comments, that.comments) && branch == that.branch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProfessor, idPersona, comments, branch);
    }
}
