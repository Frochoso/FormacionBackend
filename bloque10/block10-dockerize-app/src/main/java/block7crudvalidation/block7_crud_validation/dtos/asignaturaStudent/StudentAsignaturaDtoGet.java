package block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent;

import block7crudvalidation.block7_crud_validation.dtos.asignatura.AsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.student.StudentDtoGet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAsignaturaDtoGet {

    StudentDtoGet student;

    List<AsignaturaDtoGet> asignaturaDtoGetSet;

}
