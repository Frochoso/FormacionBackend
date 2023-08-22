package block11cors.block11_cors.dtos.asignatura;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaDtoPost {

    String nombreAsignatura;

    String comments;

    LocalDate initialDate;

    LocalDate finishDate;
}
