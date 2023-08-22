package block11cors.block11_cors.service.interfaces;

import block11cors.block11_cors.dtos.student.StudentDtoPost;
import block11cors.block11_cors.enumerations.OutputType;
import block11cors.block11_cors.dtos.student.StudentDtoGet;

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
