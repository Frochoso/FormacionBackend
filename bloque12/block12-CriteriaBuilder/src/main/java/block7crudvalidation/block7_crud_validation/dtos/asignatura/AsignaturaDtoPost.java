package block7crudvalidation.block7_crud_validation.dtos.asignatura;

import block7crudvalidation.block7_crud_validation.dtos.student.StudentDtoPost;
import lombok.*;

import java.time.LocalDate;
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
}
