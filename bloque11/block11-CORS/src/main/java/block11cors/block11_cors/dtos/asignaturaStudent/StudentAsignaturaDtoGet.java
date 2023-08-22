package block11cors.block11_cors.dtos.asignaturaStudent;

import block11cors.block11_cors.dtos.asignatura.AsignaturaDtoGet;
import block11cors.block11_cors.dtos.student.StudentDtoGet;
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
