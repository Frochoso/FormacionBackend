package block7crudvalidation.block7_crud_validation.service.interfaces;

import block7crudvalidation.block7_crud_validation.dtos.student.StudentDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.student.StudentDtoPost;
import block7crudvalidation.block7_crud_validation.enumerations.OutputType;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentDtoGet addStudent(StudentDtoPost studentDtoPost);

    StudentDtoGet getStudentById(Integer idStudent, Optional<OutputType> outputType);

    List<StudentDtoGet> getStudentsByName(String name, Optional<OutputType> outputType);

    List<StudentDtoGet> findAllStudents();

    StudentDtoGet updateStudent(Integer idStudent, StudentDtoPost studentDtoPost);

    StudentDtoGet deleteStudent(Integer idStudent);

}
