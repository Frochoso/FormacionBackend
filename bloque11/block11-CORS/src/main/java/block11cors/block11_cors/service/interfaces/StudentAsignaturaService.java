package block11cors.block11_cors.service.interfaces;

import block11cors.block11_cors.dtos.asignaturaStudent.StudentAsignaturaDto;
import block11cors.block11_cors.domain.Student;
import block11cors.block11_cors.dtos.asignaturaStudent.StudentAsignaturaDtoGet;
import block11cors.block11_cors.dtos.asignaturaStudent.StudentAsignaturaDtoPost;

import java.util.List;

public interface StudentAsignaturaService {

    public StudentAsignaturaDtoGet findAsignaturaByStudentId(Integer id);

    public void addRelationships(StudentAsignaturaDtoPost studentAsignaturaDtoPost, Student student);

    public List<StudentAsignaturaDtoGet> removeRelationShips(List<StudentAsignaturaDto> listaEliminar);

    public List<StudentAsignaturaDtoGet> addRelationShips(List<StudentAsignaturaDto> listaInsertar);
}
