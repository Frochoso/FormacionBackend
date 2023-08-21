package block7crudvalidation.block7_crud_validation.dtos.professor;

import block7crudvalidation.block7_crud_validation.enumerations.Branch;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDtoPost {

    String comments;

    Integer idPersona;

    Branch branch;
}
