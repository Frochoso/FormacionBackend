package block11cors.block11_cors.dtos.asignatura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
}
