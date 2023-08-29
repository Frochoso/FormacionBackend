package block7crudvalidation.block7_crud_validation.service;

import block7crudvalidation.block7_crud_validation.domain.Asignatura;
import block7crudvalidation.block7_crud_validation.dtos.asignatura.AsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.asignatura.AsignaturaDtoPost;
import block7crudvalidation.block7_crud_validation.error.EntityNotFoundException;
import block7crudvalidation.block7_crud_validation.error.UnprocessableEntityException;
import block7crudvalidation.block7_crud_validation.repository.AsignaturaRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@DisplayName("Tests de la capa de servicio de Asignatura")
class AsignaturaServiceTest {

    @Autowired
    AsignaturaServiceImpl asignaturaService;

    @MockBean
    AsignaturaRepository asignaturaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test para insertar asignaturas")
    void addAsignatura() {
        //Given
        AsignaturaDtoGet asignaturaEsperada = new AsignaturaDtoGet(1, "Física",
                "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"));

        AsignaturaDtoPost asignaturaDtoPost = new AsignaturaDtoPost("Física", "La mejor",
                LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"));

        Asignatura asignatura = new Asignatura(1, "Física", "La mejor",
                LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"));

        when(asignaturaRepository.save(new Asignatura(null, "Física", "La mejor",
                LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06")))).thenReturn(asignatura);

        //When
        AsignaturaDtoGet asignaturaObtenida = asignaturaService.addAsignatura(asignaturaDtoPost);

        //Then
        assertEquals(asignaturaEsperada, asignaturaObtenida);
    }

    @Nested
    @DisplayName("Tests de obtención de asignaturas dado un id.")
    class GetAsignatura {

        @Test
        @DisplayName("Obtención errónea de asignatura.")
        void getAsignatura_error() {
            //Given
            when(asignaturaRepository.findById(1)).thenThrow(
                    new EntityNotFoundException("Asignatura con id: 1 no encontrada."));

            //When Then
            EntityNotFoundException error = assertThrows(EntityNotFoundException.class,
                    () -> asignaturaService.getAsignatura(1));
            assertEquals("Asignatura con id: 1 no encontrada.", error.getMessage());
        }

        @Test
        @DisplayName("Obtención exitosa de asignatura.")
        void getAsignatura_ok() {
            //Given
            AsignaturaDtoGet asignaturaEsperada = new AsignaturaDtoGet(1, "Física",
                    "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"));

            Asignatura asignatura = new Asignatura(1, "Física",
                    "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"));

            when(asignaturaRepository.findById(1)).thenReturn(Optional.of(asignatura));

            //When
            AsignaturaDtoGet asignaturaObtenida = asignaturaService.getAsignatura(1);

            //Then
            assertEquals(asignaturaEsperada, asignaturaObtenida);

        }

    }

    @Nested
    @DisplayName("Tests de eliminar asignatura")
    class DeleteAsignatura {

        @Test
        @DisplayName("Borrado de asignatura con estudiantes asociados.")
        void deleteAsignatura_estudiantesAsociados() {
            //Given

            Asignatura asignatura = new Asignatura(1, "Física",
                    "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"));

            when(asignaturaRepository.findAsignaturas(1)).thenReturn(asignatura);

            //When Then
            UnprocessableEntityException error = assertThrows(UnprocessableEntityException.class, () ->
                    asignaturaService.deleteAsignatura(1));
            assertEquals("No se puede borrar la asignatura con id: 1 porque tiene estudiantes asociados a ella.", error.getMessage());
        }

        @Test
        @DisplayName("Borrado de asignatura inexistente.")
        void deleteAsignatura_inexistente() {
            //Given

            Asignatura asignatura = new Asignatura(1, "Física",
                    "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"));

            when(asignaturaRepository.findAsignaturas(1)).thenReturn(null);
            when(asignaturaRepository.findById(1)).thenThrow(
                    new EntityNotFoundException("Asignatura con id: 1 no encontrada."));

            //When Then
            EntityNotFoundException error = assertThrows(EntityNotFoundException.class, () ->
                    asignaturaService.deleteAsignatura(1));
            assertEquals("Asignatura con id: 1 no encontrada.", error.getMessage());
        }

        @Test
        @DisplayName("Borrado de asignatura exitoso.")
        void deleteAsignatura_ok() {
            //Given
            AsignaturaDtoGet asignaturaEsperada = new AsignaturaDtoGet(1, "Física",
                    "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"));

            Asignatura asignatura = new Asignatura(1, "Física",
                    "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"));

            when(asignaturaRepository.findAsignaturas(1)).thenReturn(null);
            when(asignaturaRepository.findById(1)).thenReturn(
                    Optional.of(asignatura));
            doNothing().when(asignaturaRepository).delete(asignatura);

            //When
            AsignaturaDtoGet asignaturaObtenida = asignaturaService.deleteAsignatura(1);

            //Then
            assertEquals(asignaturaEsperada, asignaturaObtenida);
        }

    }

    @Nested
    @DisplayName("Tests de actualizar asignaturas")
    class UpdateAsignatura {

        @Test
        @DisplayName("Actualización errónea.")
        void updateAsignatura_error() {
            //Given
            AsignaturaDtoPost asignaturaInsertada = new AsignaturaDtoPost("Física",
                    "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"));

            when(asignaturaRepository.findById(1)).thenThrow(
                    new EntityNotFoundException("Asignatura con id: 1 no encontrada."));

            //When Then
            EntityNotFoundException error = assertThrows(EntityNotFoundException.class, () ->
                    asignaturaService.updateAsignatura(1, asignaturaInsertada));
            assertEquals("Asignatura con id: 1 no encontrada.", error.getMessage());
        }

        @Test
        @DisplayName("Actualización exitosa.")
        void updateAsignatura_ok() {
            //Given
            AsignaturaDtoGet asignaturaEsperada = new AsignaturaDtoGet(1, "Física",
                    "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"));

            AsignaturaDtoPost asignaturaInsertada = new AsignaturaDtoPost("Física",
                    "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"));

            Asignatura asignatura = new Asignatura(1, "Física",
                    "La mejor", LocalDate.parse("2023-02-06"), LocalDate.parse("2023-09-06"));

            when(asignaturaRepository.findById(1)).thenReturn(Optional.of(asignatura));

            //When
            AsignaturaDtoGet asignaturaObtenida = asignaturaService.updateAsignatura(1, asignaturaInsertada);
            // Then
            assertEquals(asignaturaEsperada, asignaturaObtenida);

        }
    }
}
