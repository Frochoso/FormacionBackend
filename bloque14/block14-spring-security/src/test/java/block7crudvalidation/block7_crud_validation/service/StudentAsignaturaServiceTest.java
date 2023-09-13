package block7crudvalidation.block7_crud_validation.service;

import block7crudvalidation.block7_crud_validation.auxiliares.AuxiliarStudentAsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.domain.*;
import block7crudvalidation.block7_crud_validation.dtos.asignatura.AsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDto;
import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDtoPost;
import block7crudvalidation.block7_crud_validation.dtos.student.StudentDtoGet;
import block7crudvalidation.block7_crud_validation.enumerations.Branch;
import block7crudvalidation.block7_crud_validation.repository.StudentAsignaturaRepository;
import block7crudvalidation.block7_crud_validation.service.interfaces.AsignaturaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@DisplayName("Tests de la capa de servicio de StudentAsignaturas")
public class StudentAsignaturaServiceTest {

    @Autowired
    StudentAsignaturaServiceImpl studentAsignaturaService;

    @MockBean
    StudentAsignaturaRepository studentAsignaturaRepository;

    @MockBean
    AsignaturaServiceImpl asignaturaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test de inserción de relaciones.")
    void addRelationships() {

        //Given
        StudentAsignaturaDtoPost dtoRelaciones = new StudentAsignaturaDtoPost(1, Set.of(1));

        Student estudianteInsertado = new Student(1, new Persona(1, "Juan", "1234",
                "Tiburcio", "Tiburciez", "tiburcio@gmail.com",
                "tiburcio@gmail.com", "Churriana", false,
                LocalDate.parse("2021-02-06"), "www.tiburcio.com",
                LocalDate.parse("2023-08-25")), 2,
                "Juega baloncesto", new Professor(1), Branch.BACK, Set.of());

        AsignaturaDtoGet asignaturaDtoGet = new AsignaturaDtoGet(1, "Física",
                "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"));

        Asignatura asignatura = new Asignatura(1, "Física",
                "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"));

        when(asignaturaService.getAsignatura(1)).thenReturn(asignaturaDtoGet);

        //When
        studentAsignaturaService.addRelationships(dtoRelaciones, estudianteInsertado);
        // Then
        verify(studentAsignaturaRepository, times(1)).save(any(StudentAsignaturas.class));

    }

    @Test
    @DisplayName("Test de búsqueda de asignatura dado un id de estudiante.")
    void findAsignaturaByStudentId() {
        //Given
        StudentAsignaturaDtoGet estudianteAsignaturaEsperada = new StudentAsignaturaDtoGet(new StudentDtoGet(1, 1, 2,
                "Juega baloncesto", 1, Branch.BACK, Set.of()), List.of(new AsignaturaDtoGet(1, "Física",
                "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"))));

        Student student = new Student(1, new Persona(1), 2,
                "Juega baloncesto", new Professor(1), Branch.BACK, Set.of());

        when(studentAsignaturaRepository.findAsignaturasByStudentId(1)).
                thenReturn(List.of(new Asignatura(1, "Física",
                        "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"))));

        when(studentAsignaturaRepository.findStudentById(1)).thenReturn(student);
        //When
        StudentAsignaturaDtoGet estudianteAsignaturaObtenido = studentAsignaturaService.findAsignaturaByStudentId(1);

        //Then
        assertEquals(estudianteAsignaturaEsperada.getStudent(), estudianteAsignaturaObtenido.getStudent());
        assertEquals(estudianteAsignaturaEsperada.getAsignaturaDtoGetSet(), estudianteAsignaturaObtenido.getAsignaturaDtoGetSet());
    }

    @Test
    @DisplayName("Test de eliminación de relaciones entre estudiante y asignatura.")
    void removeRelationShips() {
        //Given
        List<StudentAsignaturaDtoGet> listaEsperada = List.of(new StudentAsignaturaDtoGet(new StudentDtoGet(
                1, 1, 2, "Juega baloncesto", 1, Branch.BACK
                , Set.of()), List.of(new AsignaturaDtoGet(1, "Física",
                "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06")))));

        List<StudentAsignaturaDto> listaEliminar = List.of(new StudentAsignaturaDto(1, List.of(1)));

        Student student = new Student(1, new Persona(1), 2,
                "Juega baloncesto", new Professor(1), Branch.BACK, Set.of());

        Asignatura asignatura = new Asignatura(1, "Física",
                "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"));

        List<Asignatura> asignaturaList = List.of(new Asignatura(1, "Física",
                "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06")));

        StudentAsignaturas studentAsignaturas = new StudentAsignaturas(student, asignatura);

        when(studentAsignaturaRepository.findStudentById(1)).thenReturn(student);
        when(studentAsignaturaRepository.findAsignaturasByStudentId(1)).thenReturn(asignaturaList);
        doNothing().when(studentAsignaturaRepository).delete(studentAsignaturas);
        //When
        List<StudentAsignaturaDtoGet> listaObtenida = studentAsignaturaService.removeRelationShips(listaEliminar);

        //Then
        AuxiliarStudentAsignaturaDtoGet.compararListasAuxiliarStudentAsignaturaDtoGet(listaEsperada, listaObtenida);
    }
}
