package block7crudvalidation.block7_crud_validation.service;

import block7crudvalidation.block7_crud_validation.auxiliares.AuxiliarProfessorDtoGet;
import block7crudvalidation.block7_crud_validation.domain.Persona;
import block7crudvalidation.block7_crud_validation.domain.Professor;
import block7crudvalidation.block7_crud_validation.dtos.professor.FullProfessorDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.professor.ProfessorDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.professor.ProfessorDtoPost;
import block7crudvalidation.block7_crud_validation.enumerations.Branch;
import block7crudvalidation.block7_crud_validation.enumerations.OutputType;
import block7crudvalidation.block7_crud_validation.error.UnprocessableEntityException;
import block7crudvalidation.block7_crud_validation.repository.PersonaRepository;
import block7crudvalidation.block7_crud_validation.repository.ProfessorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
@DisplayName("Tests de la capa de servicio de Profesor")
class ProfessorServiceTest {

    @Autowired
    ProfessorServiceImpl professorService;

    @MockBean
    ProfessorRepository professorRepository;

    @MockBean
    PersonaRepository personaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    @DisplayName("Tests del método addProfessor")
    class AddProfessor {

        @Test
        @DisplayName("Inserción de profesor errónea.")
        void addProfessor_error() {
            //Given
            ProfessorDtoPost profesorInsertado = new ProfessorDtoPost("Un notas", null, Branch.BACK);

            //When then
            UnprocessableEntityException error = assertThrows(
                    UnprocessableEntityException.class, () -> professorService.addProfessor(profesorInsertado
                    ));
            assertEquals("El id de persona no puede ser nulo.", error.getMessage());
        }

        @Test
        @DisplayName("Inserción correcta")
        void addProfessor_ok() {
            //Given
            ProfessorDtoPost profesorInsertado = new ProfessorDtoPost("Un notas"
                    , 1, Branch.BACK);

            ProfessorDtoGet profesorEsperado = new ProfessorDtoGet(1, 1
                    , "Un notas", Branch.BACK);

            Persona persona = new Persona(1);

            Professor professor = new Professor(null, persona
                    , "Un notas", Branch.BACK);

            when(personaRepository.findById(1)).thenReturn(Optional.of(persona));
            when(professorRepository.save(professor)).thenReturn(new Professor(1, persona
                    , "Un notas", Branch.BACK));

            //When
            ProfessorDtoGet profesorObtenido = professorService.addProfessor(profesorInsertado);

            //Then
            assertEquals(profesorEsperado, profesorObtenido);
        }


    }

    @Test
    @DisplayName("Test de actualización de profesor")
    void updateProfessor() {
        //Given
        ProfessorDtoGet profesorEsperado = new ProfessorDtoGet(1, 1, "Un notas", Branch.BACK);
        ProfessorDtoPost professorDtoPost = new ProfessorDtoPost("Un notas", 1, Branch.BACK);
        Persona persona = new Persona(1);
        Professor profesor = new Professor(1, persona, "Un fiera", Branch.FRONT);

        when(professorRepository.findById(1)).thenReturn(Optional.of(profesor));
        when(personaRepository.findById(1)).thenReturn(Optional.of(persona));
        when(professorRepository.save(profesor)).thenReturn(profesor);
        //When

        ProfessorDtoGet profesorObtenido = professorService.updateProfessor(1, professorDtoPost);

        //Then

        assertEquals(profesorEsperado, profesorObtenido);
    }

    @Test
    @DisplayName("Tests de eliminación de profesor.")
    void deleteProfessor() {
        //Given
        ProfessorDtoGet profesorEsperado = new ProfessorDtoGet(1);

        Professor professor = new Professor(1);

        when(professorRepository.findById(1)).thenReturn(Optional.of(professor));
        doNothing().when(professorRepository).delete(professor);
        //When

        ProfessorDtoGet profesorObtenido = professorService.deleteProfessor(1);

        //Then
        assertEquals(profesorEsperado, profesorObtenido);
    }

    @Nested
    @DisplayName("Tests de obtención de profesor")
    class GetProfessor {

        @Test
        @DisplayName("Obtención de profesor simple")
        void getProfessor_simple() {
            //Given
            ProfessorDtoGet profesorEsperado = new ProfessorDtoGet(1);

            Professor professor = new Professor(1);

            when(professorRepository.findById(1)).thenReturn(Optional.of(professor));
            //When
            ProfessorDtoGet profesorObtenido = professorService.getProfessor(1, Optional.of(OutputType.simple));

            //Then
            assertEquals(profesorEsperado, profesorObtenido);
        }

        @Test
        @DisplayName("Obtención de profesor full")
        void getProfessor_full() {
            //Given
            ProfessorDtoGet profesorEsperado = new FullProfessorDtoGet(1, 1,
                    "Un notas", Branch.BACK, "Juan Carlos", "1234", "Pepe",
                    "Viyuela", "pepe@viyuela.com", "pepe@viyuela.com",
                    "Mad rizz", true, LocalDate.parse("2012-02-11"), "uwu",
                    LocalDate.parse("2023-05-22"));

            Professor professor = new Professor(1, new Persona(1, "Juan Carlos", "1234", "Pepe",
                    "Viyuela", "pepe@viyuela.com", "pepe@viyuela.com",
                    "Mad rizz", true, LocalDate.parse("2012-02-11"), "uwu",
                    LocalDate.parse("2023-05-22")), "Un notas", Branch.BACK);

            when(professorRepository.findById(1)).thenReturn(Optional.of(professor));
            //When
            ProfessorDtoGet profesorObtenido = professorService.getProfessor(1, Optional.of(OutputType.full));

            //Then
            assertEquals(profesorEsperado, profesorObtenido);
        }

    }

    @Nested
    @DisplayName("Tests de obtención de profesor dado el nombre.")
    class FindProfessorByName {

        @Test
        @DisplayName("Obtención de dto simple dando un nombre de profesor")
        void findProfessorByName_simple() {
            //Given

            List<ProfessorDtoGet> listaEsperada = List.of(new ProfessorDtoGet(1));
            List<Professor> listaProfesores = List.of(new Professor(1));

            when(professorRepository.findByName("Juan")).thenReturn(listaProfesores);
            //When
            List<ProfessorDtoGet> listaObtenida = professorService.findProfessorByName("Juan"
                    , Optional.of(OutputType.simple));

            //Then
            AuxiliarProfessorDtoGet.compararListaProfessorDtoGet(listaEsperada, listaObtenida);
        }

        @Test
        @DisplayName("Obtención de dto full dando un nombre de profesor")
        void findProfessorByName_full() {
            //Given

            List<ProfessorDtoGet> listaEsperada = List.of(new FullProfessorDtoGet(1, 1,
                    "Un notas", Branch.BACK, "Juan Carlos", "1234", "Pepe",
                    "Viyuela", "pepe@viyuela.com", "pepe@viyuela.com",
                    "Mad rizz", true, LocalDate.parse("2012-02-11"), "uwu",
                    LocalDate.parse("2023-05-22")));
            List<Professor> listaProfesores = List.of(new Professor(1, new Persona(1, "Juan Carlos", "1234", "Pepe",
                    "Viyuela", "pepe@viyuela.com", "pepe@viyuela.com",
                    "Mad rizz", true, LocalDate.parse("2012-02-11"), "uwu",
                    LocalDate.parse("2023-05-22")),
                    "Un notas", Branch.BACK));

            when(professorRepository.findByName("Juan")).thenReturn(listaProfesores);
            //When
            List<ProfessorDtoGet> listaObtenida = professorService.findProfessorByName("Juan"
                    , Optional.of(OutputType.full));

            //Then
            assertEquals(listaEsperada, listaObtenida);
        }

    }

    @Nested
    @DisplayName("Obtención de todos los profesores dado el tipo de dto.")
    class FindAllProfessors {

        @Test
        @DisplayName("Obtención de todos los profesores en formato simple.")
        void findAllProfessors_simple() {
            //Given

            List<ProfessorDtoGet> listaEsperada = List.of(new ProfessorDtoGet(1));

            List<Professor> profesores = List.of(new Professor(1));

            when(professorRepository.findAll()).thenReturn(profesores);

            //When
            List<ProfessorDtoGet> listaObtenida = professorService.findAllProfessors(Optional.of(OutputType.simple));
            //Then
            AuxiliarProfessorDtoGet.compararListaProfessorDtoGet(listaEsperada, listaObtenida);
        }

        @Test
        @DisplayName("Obtención de todos los profesores en formato completo.")
        void findAllProfessors_full() {
            //Given

            List<ProfessorDtoGet> listaEsperada = List.of(new FullProfessorDtoGet(1, 1,
                    "Un notas", Branch.BACK, "Juan Carlos", "1234", "Pepe",
                    "Viyuela", "pepe@viyuela.com", "pepe@viyuela.com",
                    "Mad rizz", true, LocalDate.parse("2012-02-11"), "uwu",
                    LocalDate.parse("2023-05-22")));
            List<Professor> listaProfesores = List.of(new Professor(1, new Persona(1, "Juan Carlos", "1234", "Pepe",
                    "Viyuela", "pepe@viyuela.com", "pepe@viyuela.com",
                    "Mad rizz", true, LocalDate.parse("2012-02-11"), "uwu",
                    LocalDate.parse("2023-05-22")),
                    "Un notas", Branch.BACK));

            when(professorRepository.findAll()).thenReturn(listaProfesores);

            //When
            List<ProfessorDtoGet> listaObtenida = professorService.findAllProfessors(Optional.of(OutputType.full));
            //Then
            assertEquals(listaEsperada, listaObtenida);
        }

    }
}
