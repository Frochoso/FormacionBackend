package block7crudvalidation.block7_crud_validation.service.interfaces;

import block7crudvalidation.block7_crud_validation.domain.Student;
import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDto;
import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDtoPost;

import java.util.List;

public interface StudentAsignaturaService {

    public StudentAsignaturaDtoGet findAsignaturaByStudentId(Integer id);

    public void addRelationships(StudentAsignaturaDtoPost studentAsignaturaDtoPost, Student student);

    public List<StudentAsignaturaDtoGet> removeRelationShips(List<StudentAsignaturaDto> listaEliminar);

    public List<StudentAsignaturaDtoGet> addRelationShips(List<StudentAsignaturaDto> listaInsertar);
}
