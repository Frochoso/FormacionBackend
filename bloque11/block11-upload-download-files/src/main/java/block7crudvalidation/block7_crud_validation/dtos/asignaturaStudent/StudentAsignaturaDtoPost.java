package block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAsignaturaDtoPost {

    Integer idStudent;

    Set<Integer> idAsignaturas;
}
