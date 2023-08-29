package block7crudvalidation.block7_crud_validation.service;

import block7crudvalidation.block7_crud_validation.auxiliares.AuxiliarPersonaDtoGet;
import block7crudvalidation.block7_crud_validation.auxiliares.AuxiliarStudentDtoGet;
import block7crudvalidation.block7_crud_validation.domain.*;
import block7crudvalidation.block7_crud_validation.dtos.asignatura.AsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDto;
import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDtoPost;
import block7crudvalidation.block7_crud_validation.dtos.persona.PersonaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.professor.ProfessorDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.student.FullStudentDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.student.StudentDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.student.StudentDtoPost;
import block7crudvalidation.block7_crud_validation.enumerations.Branch;
import block7crudvalidation.block7_crud_validation.enumerations.OutputType;
import block7crudvalidation.block7_crud_validation.error.EntityNotFoundException;
import block7crudvalidation.block7_crud_validation.error.UnprocessableEntityException;
import block7crudvalidation.block7_crud_validation.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@DisplayName("Tests de la capa de servicio de Estudiante")
class StudentServiceTest {

    @Autowired
    StudentServiceImpl studentService;

    @MockBean
    ProfessorServiceImpl professorService;

    @MockBean
    PersonaServiceImpl personaService;

    @MockBean
    AsignaturaServiceImpl asignaturaService;

    @MockBean
    StudentAsignaturaServiceImpl studentAsignaturaService;

    @MockBean
    StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    @DisplayName("Tests del método addStudent")
    class AddStudent {

        @Test
        @DisplayName("Error al insertar nuevo estudiante sin ids de profesor y persona.")
        void addStudent_idsNulos() {
            //Given
            StudentDtoPost estudianteInsertado = new StudentDtoPost(null, null, null,
                    null, Set.of(), null);

            //When then
            UnprocessableEntityException obtenido = assertThrows(UnprocessableEntityException.class, () ->
                    studentService.addStudent(estudianteInsertado)
            );
            assertEquals("El id de profesor y persona no pueden ser nulos.", obtenido.getMessage());
        }

        @Test
        @DisplayName("Error al insertar nuevo estudiante sin id de profesor.")
        void addStudent_idProfesorNulo() {
            //Given
            StudentDtoPost estudianteInsertado = new StudentDtoPost(null, null, null,
                    1, Set.of(), null);

            //When then
            UnprocessableEntityException obtenido = assertThrows(UnprocessableEntityException.class, () ->
                    studentService.addStudent(estudianteInsertado)
            );
            assertEquals("El id de profesor y persona no pueden ser nulos.", obtenido.getMessage());
        }

        @Test
        @DisplayName("Error al insertar nuevo estudiante sin id de persona.")
        void addStudent_idPersonaNulo() {
            //Given
            StudentDtoPost estudianteInsertado = new StudentDtoPost(null, null, 1,
                    null, Set.of(), null);

            //When then
            UnprocessableEntityException obtenido = assertThrows(UnprocessableEntityException.class, () ->
                    studentService.addStudent(estudianteInsertado)
            );
            assertEquals("El id de profesor y persona no pueden ser nulos.", obtenido.getMessage());
        }

        @Test
        @DisplayName("Inserción de nuevo estudiante.")
        void addStudent_insercionCorrectaDeEstudiante() {

            //Given
            StudentDtoGet estudianteEsperado = new StudentDtoGet(1, 1, 2,
                    "Juega baloncesto", 1, Branch.BACK, Set.of());

            StudentDtoPost estudianteInsertado = new StudentDtoPost(2, "Juega baloncesto",
                    1, 1, Set.of(), Branch.BACK);

            Student estudiante = new Student(1, new Persona(1, "Juan", "1234",
                    "Tiburcio", "Tiburciez", "tiburcio@gmail.com",
                    "tiburcio@gmail.com", "Churriana", false,
                    LocalDate.parse("2021-02-06"), "www.tiburcio.com",
                    LocalDate.parse("2023-08-25")), 2,
                    "Juega baloncesto", new Professor(1,
                    new Persona(2), "Un notas", Branch.BACK), Branch.BACK, Set.of());

            Optional<OutputType> outputType = Optional.of((OutputType.simple));

            when(professorService.getProfessor(1, outputType)).thenReturn(new ProfessorDtoGet(1,
                    2, "Un notas", Branch.BACK));

            when(personaService.findById(1)).thenReturn(new PersonaDtoGet(1, "Juan", "1234",
                    "Tiburcio", "Tiburciez", "tiburcio@gmail.com",
                    "tiburcio@gmail.com", "Churriana", false,
                    LocalDate.parse("2021-02-06"), "www.tiburcio.com",
                    LocalDate.parse("2023-08-25")));

            when(studentRepository.save(new Student(null, new Persona(1, "Juan",
                    "1234", "Tiburcio", "Tiburciez", "tiburcio@gmail.com",
                    "tiburcio@gmail.com", "Churriana", false,
                    LocalDate.parse("2021-02-06"), "www.tiburcio.com",
                    LocalDate.parse("2023-08-25")), 2,
                    "Juega baloncesto", new Professor(1,
                    new Persona(2), "Un notas", Branch.BACK), Branch.BACK, Set.of()))).
                    thenReturn(estudiante);

            doNothing().when(studentAsignaturaService).addRelationships(
                    new StudentAsignaturaDtoPost(1, Set.of()), estudiante);

            //When
            StudentDtoGet estudianteObtenido = studentService.addStudent(estudianteInsertado);

            //Then
            assertEquals(estudianteEsperado, estudianteObtenido);
        }
    }

    @Nested
    @DisplayName("Tests del método getStudentById")
    class GetStudentById {

        @Test
        @DisplayName("Devolución de dto simple")
        void getStudentById_dtoSimple() {

            //Given
            StudentDtoGet estudianteEsperado = new StudentDtoGet(1, 1, 2,
                    "Juega baloncesto", 1, Branch.BACK, Set.of());

            Student student = new Student(1, new Persona(1), 2,
                    "Juega baloncesto", new Professor(1), Branch.BACK, Set.of());

            when(studentRepository.findById(1)).thenReturn(Optional.of(student));
            //When

            StudentDtoGet estudianteObtenido = studentService.getStudentById(1, Optional.of(OutputType.simple));

            //Then
            assertEquals(estudianteEsperado, estudianteObtenido);
        }

        @Test
        @DisplayName("Devolución de dto full")
        void getStudentById_dtoFull() {

            //Given
            StudentDtoGet estudianteEsperado = new FullStudentDtoGet(1, 1, 2,
                    "Juega baloncesto", 1, Branch.BACK, Set.of(), "Juan Carlos",
                    "123", "Pepe", "Viyuela", "pepe.viyuela@gmail.com",
                    "pepe.viyuela@gmail.com", "Mad rizz", true, LocalDate.parse("2001-05-12")
                    , "www.google.com", LocalDate.parse("2023-08-28"));

            Student student = new Student(1, new Persona(1, "Juan Carlos",
                    "123", "Pepe", "Viyuela", "pepe.viyuela@gmail.com",
                    "pepe.viyuela@gmail.com", "Mad rizz", true, LocalDate.parse("2001-05-12")
                    , "www.google.com", LocalDate.parse("2023-08-28")), 2,
                    "Juega baloncesto", new Professor(1), Branch.BACK, Set.of());

            when(studentRepository.findById(1)).thenReturn(Optional.of(student));
            //When

            StudentDtoGet estudianteObtenido = studentService.getStudentById(1, Optional.of(OutputType.full));

            //Then
            assertEquals(estudianteEsperado, estudianteObtenido);
        }

    }

    @Nested
    @DisplayName("Tests de obtención de estudiantes dado un nombre.")
    class GetStudentsByName {

        @Test
        @DisplayName("Obtención simple de estudiantes dado un nombre.")
        void getStudentsByName_simple() {
            //Given

            List<StudentDtoGet> listaEsperada = List.of(new StudentDtoGet(1, 1, 2,
                    "Juega baloncesto", 1, Branch.BACK, Set.of()));

            List<Student> estudiantes = List.of(new Student(1, new Persona(1, "Juan", "1234",
                    "Tiburcio", "Tiburciez", "tiburcio@gmail.com",
                    "tiburcio@gmail.com", "Churriana", false,
                    LocalDate.parse("2021-02-06"), "www.tiburcio.com",
                    LocalDate.parse("2023-08-25")), 2,
                    "Juega baloncesto", new Professor(1), Branch.BACK, Set.of()));

            when(studentRepository.findByName("Juan")).thenReturn(estudiantes);
            //When
            List<StudentDtoGet> listaObtenida = studentService.getStudentsByName("Juan"
                    , Optional.of(OutputType.simple));
            //Then
            AuxiliarStudentDtoGet.compararListaEstudiantesDtoGet(listaEsperada, listaObtenida);
        }

        @Test
        @DisplayName("Obtención full de estudiantes dado un nombre.")
        void getStudentsByName_full() {
            //Given

            List<StudentDtoGet> listaEsperada = List.of(new StudentDtoGet(1, 1, 2,
                    "Juega baloncesto", 1, Branch.BACK, Set.of()));

            List<Student> estudiantes = List.of(new Student(1, new Persona(1, "Juan", "1234",
                    "Tiburcio", "Tiburciez", "tiburcio@gmail.com",
                    "tiburcio@gmail.com", "Churriana", false,
                    LocalDate.parse("2021-02-06"), "www.tiburcio.com",
                    LocalDate.parse("2023-08-25")), 2,
                    "Juega baloncesto", new Professor(1), Branch.BACK, Set.of()));

            when(studentRepository.findByName("Juan")).thenReturn(estudiantes);
            //When
            List<StudentDtoGet> listaObtenida = studentService.getStudentsByName("Juan"
                    , Optional.of(OutputType.full));
            //Then
            AuxiliarStudentDtoGet.compararListaEstudiantesDtoGet(listaEsperada, listaObtenida);
        }
    }

    @Test
    @DisplayName("Test del findAll")
    void findAllStudents() {

        //Given
        List<StudentDtoGet> estudiantesEsperados = List.of(new StudentDtoGet(1, 1, 2,
                "Juega baloncesto", 1, Branch.BACK, Set.of()));

        when(studentRepository.findAll()).thenReturn(List.of(new Student(1, new Persona(1),
                2, "Juega baloncesto", new Professor(1), Branch.BACK, Set.of())));

        //When
        List<StudentDtoGet> estudiantesObtenidos = studentService.findAllStudents();

        //Then
        AuxiliarStudentDtoGet.compararListaEstudiantesDtoGet(estudiantesEsperados, estudiantesObtenidos);
    }

    @Nested
    @DisplayName("Test de eliminación de estudiante.")
    class DeleteStudent {

        @Test
        @DisplayName("Test de eliminación de estudiante correctamente.")
        void deleteStudent_ok() {
            //Given
            StudentDtoGet estudianteEsperado = new StudentDtoGet(1, 1, 2,
                    "Juega baloncesto", 1, Branch.BACK, Set.of(1));

            Asignatura asignatura = new Asignatura(1);

            Student student = new Student(1, new Persona(1, "Juan", "1234",
                    "Tiburcio", "Tiburciez", "tiburcio@gmail.com",
                    "tiburcio@gmail.com", "Churriana", false,
                    LocalDate.parse("2021-02-06"), "www.tiburcio.com",
                    LocalDate.parse("2023-08-25")), 2,
                    "Juega baloncesto", new Professor(1,
                    new Persona(2), "Un notas", Branch.BACK), Branch.BACK, Set.of());

            StudentAsignaturas studentAsignaturas = new StudentAsignaturas(student, asignatura);

            StudentAsignaturaDto studentAsignaturaDto = new StudentAsignaturaDto(1, List.of(1));

            student.setAsignaturas(Set.of(studentAsignaturas));

            AsignaturaDtoGet asignaturaDtoGet = new AsignaturaDtoGet(1);

            StudentAsignaturaDtoGet estudianteAsignaturaDtoGet = new StudentAsignaturaDtoGet(estudianteEsperado,
                    List.of(asignaturaDtoGet));

            when(studentRepository.findById(1)).thenReturn(Optional.of(student));
            when(studentAsignaturaService.findAsignaturaByStudentId(1)).thenReturn(estudianteAsignaturaDtoGet);
            when(studentAsignaturaService.removeRelationShips(List.of(studentAsignaturaDto))).thenReturn(
                    List.of(estudianteAsignaturaDtoGet));
            doNothing().when(studentRepository).delete(student);

            //When

            StudentDtoGet estudianteObtenido = studentService.deleteStudent(1);

            //Then
            assertEquals(estudianteEsperado, estudianteObtenido);
        }

        @Test
        @DisplayName("Test de eliminación de estudiante error.")
        void deleteStudent_error() {
            //Given
            StudentDtoGet estudianteEsperado = new StudentDtoGet(1, 1, 2,
                    "Juega baloncesto", 1, Branch.BACK, Set.of());

            when(studentRepository.findById(1)).thenThrow(new EntityNotFoundException(
                    "Estudiante con id: 1 no existe."
            ));

            //When Then
            EntityNotFoundException error = assertThrows(EntityNotFoundException.class, () ->
                    studentService.deleteStudent(1));
            assertEquals("Estudiante con id: 1 no existe.", error.getMessage());
        }
    }

    @Test
    @DisplayName("Test de actualización de estudiante.")
    void updateStudent() {
        //Given
        StudentDtoGet estudianteEsperado = new StudentDtoGet(1, 1, 2,
                "Un notas", 2, Branch.FRONT, Set.of(1));

        StudentDtoPost estudianteInsertado = new StudentDtoPost(2, "Un notas", 2,
                1, Set.of(1), Branch.FRONT);

        Student student = new Student(1, new Persona(1, "Juan", "1234",
                "Tiburcio", "Tiburciez", "tiburcio@gmail.com",
                "tiburcio@gmail.com", "Churriana", false,
                LocalDate.parse("2021-02-06"), "www.tiburcio.com",
                LocalDate.parse("2023-08-25")), 2,
                "Juega baloncesto", new Professor(1,
                new Persona(2), "Un notas", Branch.BACK), Branch.BACK, Set.of());


        PersonaDtoGet persona = new PersonaDtoGet(1, "Eusebio", "1234", "Carlos",
                "Perez", "juanCarlos@gmail.com", "juanCarlos@gmail.com",
                "La ciudad no puede estar vacía", false, LocalDate.parse("2023-02-01"),
                "Hola", LocalDate.parse("2023-08-01"));

        AsignaturaDtoGet asignatura = new AsignaturaDtoGet(1);

        ProfessorDtoGet professor = new ProfessorDtoGet(2, 2, "", Branch.FRONT);

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(personaService.findById(1)).thenReturn(persona);
        when(professorService.getProfessor(2, Optional.of(OutputType.simple))).thenReturn(professor);
        when(asignaturaService.getAsignatura(1)).thenReturn(asignatura);
        when(studentRepository.save(student)).thenReturn(student);

        //When
        StudentDtoGet estudianteObtenido = studentService.updateStudent(1, estudianteInsertado);

        //Then
        assertEquals(estudianteEsperado, estudianteObtenido);
    }
}
