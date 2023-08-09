package block7crud.block7_crud.converter;

import block7crud.block7_crud.domain.Student;
import block7crud.block7_crud.dtos.student.StudentDtoGet;
import block7crud.block7_crud.dtos.student.StudentDtoPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StudentMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastName", source = "lastName")
    StudentDtoGet studentToDtoGet(Student student);

    @Mapping(target ="name",source="name")
    @Mapping(target = "lastName", source = "lastName")
    Student studentDtoGetToStudent(StudentDtoGet studentDtoGet);

    @Mapping(target ="name",source="name")
    @Mapping(target = "lastName", source = "lastName")
    StudentDtoGet studentDtoPostToStudentDtoGet(StudentDtoPost studentDtoPost);

}
