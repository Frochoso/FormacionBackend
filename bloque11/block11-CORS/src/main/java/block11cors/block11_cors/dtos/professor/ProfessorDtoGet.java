package block11cors.block11_cors.dtos.professor;

import block11cors.block11_cors.enumerations.Branch;
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
