package block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAsignaturaDto {

    Integer idStudent;

    List<Integer> asignaturas;

}
