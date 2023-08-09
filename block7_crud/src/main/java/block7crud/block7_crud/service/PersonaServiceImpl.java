package block7crud.block7_crud.service;

import block7crud.block7_crud.converter.StudentMapper;
import block7crud.block7_crud.dtos.student.StudentDtoGet;
import block7crud.block7_crud.dtos.student.StudentDtoPost;
import block7crud.block7_crud.repository.StudentRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    StudentMapper mapper = Mappers.getMapper(StudentMapper.class);

    @Autowired
    StudentRepository studentRepository;

    @Override
    public StudentDtoGet addStudent(StudentDtoPost student) {
        return mapper.studentDtoPostToStudentDtoGet(student);
    }

    @Override
    public StudentDtoGet getStudentById(int id) {
        return null;
    }

    @Override
    public void deleteStudentById(int id) {

    }

    @Override
    public Iterable<StudentDtoGet> getAllStudents(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public StudentDtoGet updateStudent(StudentDtoPost student) {
        return null;
    }
}
