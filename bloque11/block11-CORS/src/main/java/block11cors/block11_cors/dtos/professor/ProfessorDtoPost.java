package block11cors.block11_cors.dtos.professor;

import block11cors.block11_cors.enumerations.Branch;
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
