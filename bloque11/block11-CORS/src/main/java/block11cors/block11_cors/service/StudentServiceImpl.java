package block11cors.block11_cors.service;

import block11cors.block11_cors.converters.PersonaMapper;
import block11cors.block11_cors.domain.Student;
import block11cors.block11_cors.dtos.asignatura.AsignaturaDtoGet;
import block11cors.block11_cors.dtos.asignaturaStudent.StudentAsignaturaDto;
import block11cors.block11_cors.dtos.student.StudentDtoPost;
import block11cors.block11_cors.enumerations.OutputType;
import block11cors.block11_cors.error.EntityNotFoundException;
import block11cors.block11_cors.error.UnprocessableEntityException;
import block11cors.block11_cors.repository.StudentRepository;
import block11cors.block11_cors.service.interfaces.PersonaService;
import block11cors.block11_cors.converters.ProfessorMapper;
import block11cors.block11_cors.converters.StudentMapper;
import block11cors.block11_cors.service.interfaces.ProfessorService;
import block11cors.block11_cors.service.interfaces.StudentAsignaturaService;
import block11cors.block11_cors.service.interfaces.StudentService;
import block11cors.block11_cors.dtos.asignaturaStudent.StudentAsignaturaDtoGet;
import block11cors.block11_cors.dtos.asignaturaStudent.StudentAsignaturaDtoPost;
import block11cors.block11_cors.dtos.student.StudentDtoGet;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);
    PersonaMapper personaMapper = Mappers.getMapper(PersonaMapper.class);
    ProfessorMapper professorMapper = Mappers.getMapper(ProfessorMapper.class);

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PersonaService personaService;

    @Autowired
    ProfessorService professorService;

    @Autowired
    StudentAsignaturaService studentAsignaturaService;

    @Override
    public StudentDtoGet addStudent(StudentDtoPost studentDtoPost) {
        if (studentDtoPost.getIdProfessor() == null || studentDtoPost.getIdPersona() == null) {
            throw new UnprocessableEntityException("El id de profesor y persona no pueden ser nulos.");
        }

        Optional<OutputType> outputType = Optional.of((OutputType.simple));

        Student student = studentMapper.studentDtoPostToStudent(studentDtoPost);

        student.setProfessor(professorMapper.professorDtoGetToProfessor(professorService.getProfessor(studentDtoPost.getIdProfessor(), outputType)));
        student.setPersona(personaMapper.convertPersonaDtoGetToPersona(personaService.findById(studentDtoPost.getIdPersona())));

        StudentDtoGet resultado = studentMapper.studentToStudentDtoGet(studentRepository.save(student));
        studentAsignaturaService.addRelationships(new StudentAsignaturaDtoPost(student.getIdStudent(), studentDtoPost.getAsignaturas()), student);

        return resultado;
    }

    @Override
    public StudentDtoGet getStudentById(Integer idStudent, Optional<OutputType> outputType) {

        OutputType type = outputType.orElse(OutputType.simple);

        Student student = studentRepository.findById(idStudent).orElseThrow(() -> new EntityNotFoundException("Estudiante con id: " + idStudent + " no encontrado."));
        StudentDtoGet dtoDevuelto = new StudentDtoGet();

        switch (type) {
            case simple:
                dtoDevuelto = studentMapper.studentToStudentDtoGet(student);
                break;
            case full:
                dtoDevuelto = studentMapper.createNewFullStudentDtoGet(student, student.getPersona());
                break;
        }

        return dtoDevuelto;
    }

    @Override
    public List<StudentDtoGet> getStudentsByName(String name, Optional<OutputType> outputType) {

        OutputType type = outputType.orElse(OutputType.simple);

        List<Student> studentList = studentRepository.findByName(name);
        List<StudentDtoGet> dtoDevuelto = new ArrayList<>();

        switch (type) {
            case simple:
                dtoDevuelto = studentMapper.studentToStudentDtoGet(studentList);
                break;
            case full:
                for (Student student : studentList) {
                    dtoDevuelto.add(studentMapper.createNewFullStudentDtoGet(student, student.getPersona()));
                }
                break;
        }

        return dtoDevuelto;
    }

    @Override
    public List<StudentDtoGet> findAllStudents() {
        return studentMapper.studentToStudentDtoGet(studentRepository.findAll());
    }

    @Override
    public StudentDtoGet updateStudent(Integer idStudent, StudentDtoPost studentDtoPost) {
        return null;
    }

    @Override
    public StudentDtoGet deleteStudent(Integer idStudent) {
        Student student = studentRepository.findById(idStudent).orElseThrow(() ->
                new EntityNotFoundException("Estudiante con id: " + idStudent + " no encontrado."));

        StudentAsignaturaDtoGet studentAsignaturaDtoGet = studentAsignaturaService.findAsignaturaByStudentId(idStudent);

        List<StudentAsignaturaDto> studentAsignaturaDtoList = new ArrayList<>();

        for (AsignaturaDtoGet asignaturas : studentAsignaturaDtoGet.getAsignaturaDtoGetSet()) {
            studentAsignaturaDtoList.add(new StudentAsignaturaDto(idStudent, List.of(asignaturas.getIdAsignatura())));
        }

        studentAsignaturaService.removeRelationShips(studentAsignaturaDtoList);
        studentRepository.delete(student);

        return studentMapper.studentToStudentDtoGet(student);
    }
}
