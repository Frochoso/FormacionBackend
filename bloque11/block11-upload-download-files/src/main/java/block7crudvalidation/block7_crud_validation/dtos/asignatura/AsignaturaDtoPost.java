package block7crudvalidation.block7_crud_validation.dtos.asignatura;

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
