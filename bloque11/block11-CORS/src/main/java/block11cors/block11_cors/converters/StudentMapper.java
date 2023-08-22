package block11cors.block11_cors.converters;

import block11cors.block11_cors.domain.StudentAsignaturas;
import block11cors.block11_cors.dtos.student.StudentDtoPost;
import block11cors.block11_cors.domain.Asignatura;
import block11cors.block11_cors.domain.Persona;
import block11cors.block11_cors.domain.Student;
import block11cors.block11_cors.dtos.student.FullStudentDtoGet;
import block11cors.block11_cors.dtos.student.StudentDtoGet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper
public interface StudentMapper {

    @Mapping(target = "id_persona", source = "persona.idPersona")
    @Mapping(target = "id_professor", source = "professor.idProfesor")
    @Mapping(target = "asignaturas", expression = "java(mapStudentAsignaturasToAsignaturaDtoGet(student.getAsignaturas()))")
    StudentDtoGet studentToStudentDtoGet(Student student);

    default Set<Integer> mapStudentAsignaturasToAsignaturaDtoGet(Set<StudentAsignaturas> asignaturas) {
        return asignaturas.stream()
                .map(StudentAsignaturas::getAsignatura)
                .map(Asignatura::getIdAsignatura)
                .collect(Collectors.toSet());
    }

    @Mapping(target = "persona.idPersona", source = "id_persona")
    @Mapping(target = "professor.idProfesor", source = "id_professor")
    @Mapping(target = "asignaturas", ignore = true)
    Student studentDtoGetToStudent(StudentDtoGet studentDtoGet);

    List<StudentDtoGet> studentToStudentDtoGet(List<Student> student);

    @Mapping(target = "branch", source = "branch")
    @Mapping(target = "asignaturas", expression = "java(mapAsignaturaIdsToStudentAsignaturas(studentDtoPost.getAsignaturas()))")
    Student studentDtoPostToStudent(StudentDtoPost studentDtoPost);

    default Set<StudentAsignaturas> mapAsignaturaIdsToStudentAsignaturas(Set<Integer> asignaturaIds) {
        Set<StudentAsignaturas> studentAsignaturas = new HashSet<>();
        for (Integer asignaturaId : asignaturaIds) {
            Asignatura asignatura = new Asignatura();
            asignatura.setIdAsignatura(asignaturaId);

            StudentAsignaturas studentAsignatura = new StudentAsignaturas();
            studentAsignatura.setAsignatura(asignatura);
            studentAsignaturas.add(studentAsignatura);
        }
        return studentAsignaturas;
    }

    @Mapping(target = "asignaturas", source = "student.asignaturas", qualifiedByName = "mapStudentAsignaturasToIds")
    FullStudentDtoGet createNewFullStudentDtoGet(Student student, Persona persona);

    @Named("mapStudentAsignaturasToIds")
    default Set<Integer> mapStudentAsignaturasToIds(Set<StudentAsignaturas> asignaturas) {
        return asignaturas.stream()
                .map(StudentAsignaturas::getAsignatura)
                .map(Asignatura::getIdAsignatura)
                .collect(Collectors.toSet());
    }
}
