package block7crud.block7_crud.service;

import block7crud.block7_crud.dtos.student.StudentDtoGet;
import block7crud.block7_crud.dtos.student.StudentDtoPost;

public interface StudentService {

    StudentDtoGet addStudent(StudentDtoPost student);

    StudentDtoGet getStudentById(int id);

    void deleteStudentById(int id);

    Iterable<StudentDtoGet> getAllStudents(int pageNumber, int pageSize);

    StudentDtoGet updateStudent(StudentDtoPost student);

}
