package block7crudvalidation.block7_crud_validation.dtos.professor;

import block7crudvalidation.block7_crud_validation.enumerations.Branch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDtoGet {

    Integer idProfessor;

    Integer idPersona;

    String comments;

    Branch branch;

}
