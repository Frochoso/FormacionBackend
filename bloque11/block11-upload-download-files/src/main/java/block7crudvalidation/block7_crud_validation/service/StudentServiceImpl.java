package block7crudvalidation.block7_crud_validation.service;

import block7crudvalidation.block7_crud_validation.converters.PersonaMapper;
import block7crudvalidation.block7_crud_validation.converters.ProfessorMapper;
import block7crudvalidation.block7_crud_validation.converters.StudentMapper;
import block7crudvalidation.block7_crud_validation.domain.*;
import block7crudvalidation.block7_crud_validation.dtos.asignatura.AsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDto;
import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDtoPost;
import block7crudvalidation.block7_crud_validation.dtos.student.StudentDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.student.StudentDtoPost;
import block7crudvalidation.block7_crud_validation.enumerations.OutputType;
import block7crudvalidation.block7_crud_validation.error.EntityNotFoundException;
import block7crudvalidation.block7_crud_validation.error.UnprocessableEntityException;
import block7crudvalidation.block7_crud_validation.repository.StudentRepository;
import block7crudvalidation.block7_crud_validation.service.interfaces.*;
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
