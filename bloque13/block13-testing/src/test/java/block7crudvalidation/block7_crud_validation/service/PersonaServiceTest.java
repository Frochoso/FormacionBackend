package block7crudvalidation.block7_crud_validation.service;

import block7crudvalidation.block7_crud_validation.auxiliares.AuxiliarPersonaDtoGet;
import block7crudvalidation.block7_crud_validation.domain.Persona;
import block7crudvalidation.block7_crud_validation.domain.Professor;
import block7crudvalidation.block7_crud_validation.domain.Student;
import block7crudvalidation.block7_crud_validation.dtos.persona.PersonaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.persona.PersonaDtoPost;
import block7crudvalidation.block7_crud_validation.enumerations.Branch;
import block7crudvalidation.block7_crud_validation.error.UnprocessableEntityException;
import block7crudvalidation.block7_crud_validation.repository.PersonaRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@DisplayName("Test de PersonaService")
class PersonaServiceTest {

    @Autowired
    PersonaServiceImpl personaService;

    @MockBean
    PersonaRepository personaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Obtención de una persona dado su id.")
    void findById() {
        //Given
        PersonaDtoGet personaDtoGetEsperada = new PersonaDtoGet(1, null, null,
                null, null, null, null, null, false,
                null, null, null);

        Optional<Persona> persona = Optional.of((new Persona(1, null, null,
                null, null, null, null, null, false,
                null, null, null)));

        when(personaRepository.findById(1)).thenReturn(persona);

        //When
        PersonaDtoGet personaDtoGetObtenida = personaService.findById(1);
        //Then
        assertEquals(personaDtoGetEsperada, personaDtoGetObtenida);

    }

    @Test
    @DisplayName("Obtención de una lista de usuarios dando un nombre.")
    void findByUsuario() {
        //Given
        List<PersonaDtoGet> listaEsperada = List.of(new PersonaDtoGet(1, "Juan Carlos",
                null, null, null, null, null, null,
                false, null, null, null));

        List<Persona> listaPersonas = List.of(new Persona(1, "Juan Carlos", null,
                null, null, null, null, null, false,
                null, null, null));

        when(personaRepository.findByUsuario("Juan Carlos")).thenReturn(listaPersonas);
        //When
        List<PersonaDtoGet> listaObtenida = personaService.findByUsuario("Juan Carlos");
        //Then
        AuxiliarPersonaDtoGet.compararListaPersonaDtoGet(listaEsperada, listaObtenida);
    }

    @Test
    @DisplayName("Obtención de una lista de todos los usuarios")
    void findAll() {
        //Given
        List<PersonaDtoGet> listaEsperada = List.of(new PersonaDtoGet(1, "Juan Carlos",
                null, null, null, null, null, null,
                false, null, null, null));

        List<Persona> listaPersonas = List.of(new Persona(1, "Juan Carlos", null,
                null, null, null, null, null, false,
                null, null, null));

        when(personaRepository.findAll()).thenReturn(listaPersonas);

        //When
        List<PersonaDtoGet> listaObtenida = personaService.findAll();

        //Then
        AuxiliarPersonaDtoGet.compararListaPersonaDtoGet(listaEsperada, listaObtenida);
    }

    @DisplayName("Pruebas de inserción de nuevas personas.")
    @Nested
    class AddUser {

        @Test
        void addUser_usuarioNulo() {
            //Given
            PersonaDtoPost personaInsertada = new PersonaDtoPost(null, null, null, null,
                    null, null, null, false, null, null,
                    null);

            //When then
            UnprocessableEntityException obtenida = assertThrows(UnprocessableEntityException.class, () ->
                    personaService.addUser(personaInsertada)
            );
            assertEquals("El usuario no puede ser nulo", obtenida.getMessage());

        }

        @Test
        void addUser_usuarioLongitudInferiorA6() {
            //Given
            PersonaDtoPost personaInsertada = new PersonaDtoPost("As", null, null, null,
                    null, null, null, false, null, null,
                    null);

            //When then
            UnprocessableEntityException obtenida = assertThrows(UnprocessableEntityException.class, () ->
                    personaService.addUser(personaInsertada)
            );
            assertEquals("La longitud mínima es de 6", obtenida.getMessage());

        }

        @Test
        void addUser_usuarioLongitudSuperiorA10() {
            //Given
            PersonaDtoPost personaInsertada = new PersonaDtoPost("Juan Carlos Manolo", null, null, null,
                    null, null, null, false, null, null,
                    null);

            //When then
            UnprocessableEntityException obtenida = assertThrows(UnprocessableEntityException.class, () ->
                    personaService.addUser(personaInsertada)
            );
            assertEquals("La longitud máxima es de 10", obtenida.getMessage());

        }

        @Test
        void addUser_passwordNula() {
            //Given
            PersonaDtoPost personaInsertada = new PersonaDtoPost("Eusebio", null, null, null,
                    null, null, null, false, null, null,
                    null);

            //When then
            UnprocessableEntityException obtenida = assertThrows(UnprocessableEntityException.class, () ->
                    personaService.addUser(personaInsertada)
            );
            assertEquals("La contraseña no puede ser nula", obtenida.getMessage());

        }

        @Test
        void addUser_nombreNulo() {
            //Given
            PersonaDtoPost personaInsertada = new PersonaDtoPost("Eusebio", "1234", null, null,
                    null, null, null, false, null, null,
                    null);

            //When then
            UnprocessableEntityException obtenida = assertThrows(UnprocessableEntityException.class, () ->
                    personaService.addUser(personaInsertada)
            );
            assertEquals("El nombre no puede estar vacío", obtenida.getMessage());

        }

        @Test
        void addUser_apellidoNulo() {
            //Given
            PersonaDtoPost personaInsertada = new PersonaDtoPost("Eusebio", "1234", "Carlos", null,
                    null, null, null, false, null, null,
                    null);

            //When then
            UnprocessableEntityException obtenida = assertThrows(UnprocessableEntityException.class, () ->
                    personaService.addUser(personaInsertada)
            );
            assertEquals("El apellido no puede estar vacío", obtenida.getMessage());

        }

        @Test
        void addUser_emailCorporativoNulo() {
            //Given
            PersonaDtoPost personaInsertada = new PersonaDtoPost("Eusebio", "1234", "Carlos", "Perez",
                    null, null, null, false, null, null,
                    null);

            //When then
            UnprocessableEntityException obtenida = assertThrows(UnprocessableEntityException.class, () ->
                    personaService.addUser(personaInsertada)
            );
            assertEquals("El email corporativo no puede estar vacío", obtenida.getMessage());

        }

        @Test
        void addUser_emailPersonalNulo() {
            //Given
            PersonaDtoPost personaInsertada = new PersonaDtoPost("Eusebio", "1234", "Carlos", "Perez",
                    "juanCarlos@gmail.com", null, null, false, null, null,
                    null);

            //When then
            UnprocessableEntityException obtenida = assertThrows(UnprocessableEntityException.class, () ->
                    personaService.addUser(personaInsertada)
            );
            assertEquals("El email personal no puede estar vacío", obtenida.getMessage());

        }

        @Test
        void addUser_ciudadNula() {
            //Given
            PersonaDtoPost personaInsertada = new PersonaDtoPost("Eusebio", "1234", "Carlos",
                    "Perez", "juanCarlos@gmail.com", "juanCarlos@gmail.com",
                    null, false, null,
                    null, null);

            //When then
            UnprocessableEntityException obtenida = assertThrows(UnprocessableEntityException.class, () ->
                    personaService.addUser(personaInsertada)
            );
            assertEquals("La ciudad no puede estar vacía", obtenida.getMessage());

        }

        @Test
        void addUser_fechaCreacionNula() {
            //Given
            PersonaDtoPost personaInsertada = new PersonaDtoPost("Eusebio", "1234", "Carlos",
                    "Perez", "juanCarlos@gmail.com", "juanCarlos@gmail.com",
                    "La ciudad no puede estar vacía", false, null,
                    null, null);

            //When then
            UnprocessableEntityException obtenida = assertThrows(UnprocessableEntityException.class, () ->
                    personaService.addUser(personaInsertada)
            );
            assertEquals("La fecha de creación no puede ser nula", obtenida.getMessage());

        }

        @Test
        void addUser_imagenNula() {
            //Given
            PersonaDtoPost personaInsertada = new PersonaDtoPost("Eusebio", "1234", "Carlos",
                    "Perez", "juanCarlos@gmail.com", "juanCarlos@gmail.com",
                    "La ciudad no puede estar vacía", false, LocalDate.parse("2023-02-01"),
                    null, null);

            //When then
            UnprocessableEntityException obtenida = assertThrows(UnprocessableEntityException.class, () ->
                    personaService.addUser(personaInsertada)
            );
            assertEquals("La imagen debe ser especificada", obtenida.getMessage());

        }

        @Test
        void addUser_fechaFiniquitoNula() {
            //Given
            PersonaDtoPost personaInsertada = new PersonaDtoPost("Eusebio", "1234", "Carlos",
                    "Perez", "juanCarlos@gmail.com", "juanCarlos@gmail.com",
                    "La ciudad no puede estar vacía", false, LocalDate.parse("2023-02-01"),
                    "hola", null);

            //When then
            UnprocessableEntityException obtenida = assertThrows(UnprocessableEntityException.class, () ->
                    personaService.addUser(personaInsertada)
            );
            assertEquals("La fecha de finiquito no puede ser nula", obtenida.getMessage());

        }

        @Test
        void addUser_insercionCorrecta() {
            //Given
            PersonaDtoGet personaEsperada = new PersonaDtoGet(1, "Eusebio", "1234",
                    "Carlos", "Perez", "juanCarlos@gmail.com",
                    "juanCarlos@gmail.com", "La ciudad no puede estar vacía", false,
                    LocalDate.parse("2023-02-01"), "Hola", LocalDate.parse("2023-08-01"));

            PersonaDtoPost personaInsertada = new PersonaDtoPost("Eusebio", "1234", "Carlos",
                    "Perez", "juanCarlos@gmail.com", "juanCarlos@gmail.com",
                    "La ciudad no puede estar vacía", false, LocalDate.parse("2023-02-01"),
                    "Hola", LocalDate.parse("2023-08-01"));

            Persona persona = new Persona(null, "Eusebio", "1234",
                    "Carlos", "Perez", "juanCarlos@gmail.com",
                    "juanCarlos@gmail.com", "La ciudad no puede estar vacía", false,
                    LocalDate.parse("2023-02-01"), "Hola", LocalDate.parse("2023-08-01"));

            when(personaRepository.save(persona))
                    .thenReturn(new Persona(1, "Eusebio", "1234",
                            "Carlos", "Perez", "juanCarlos@gmail.com",
                            "juanCarlos@gmail.com", "La ciudad no puede estar vacía", false,
                            LocalDate.parse("2023-02-01"), "Hola", LocalDate.parse("2023-08-01")));

            //When
            PersonaDtoGet personaObtenida = personaService.addUser(personaInsertada);

            //Then
            assertEquals(personaEsperada, personaObtenida);

        }
    }


    @Nested
    @DisplayName("Tests de eliminación de persona")
    class DeleteUser {

        @Test
        @DisplayName("Eliminación errónea de una persona.")
        void deleteUser_error() {
            //Given
            Student studentAsociado = new Student(1, new Persona(1, "Juan", "1234",
                    "Tiburcio", "Tiburciez", "tiburcio@gmail.com",
                    "tiburcio@gmail.com", "Churriana", false,
                    LocalDate.parse("2021-02-06"), "www.tiburcio.com",
                    LocalDate.parse("2023-08-25")), 2,
                    "Juega baloncesto", new Professor(1,
                    new Persona(2), "Un notas", Branch.BACK), Branch.BACK, Set.of());

            //When then
            when(personaRepository.findStudentAsociado(1)).
                    thenReturn(studentAsociado);

            UnprocessableEntityException obtenida = assertThrows(UnprocessableEntityException.class, () ->
                    personaService.deleteUser(1));

            assertEquals("No se puede borrar esta persona porque tiene un id de estudiante asociado."
                    , obtenida.getMessage());
        }

        @Test
        @DisplayName("Eliminación de persona dado su id.")
        void deleteUser_ok() {
            //Given
            PersonaDtoGet personaEsperada = new PersonaDtoGet(1, "Eusebio", "1234", "Carlos",
                    "Perez", "juanCarlos@gmail.com", "juanCarlos@gmail.com",
                    "La ciudad no puede estar vacía", false, LocalDate.parse("2023-02-01"),
                    "Hola", LocalDate.parse("2023-08-01"));

            Persona persona = new Persona(1, "Eusebio", "1234", "Carlos",
                    "Perez", "juanCarlos@gmail.com", "juanCarlos@gmail.com",
                    "La ciudad no puede estar vacía", false, LocalDate.parse("2023-02-01"),
                    "Hola", LocalDate.parse("2023-08-01"));

            when(personaRepository.findById(1)).thenReturn(Optional.of(persona));
            doNothing().when(personaRepository).delete(persona);

            //When
            PersonaDtoGet personaObtenida = personaService.deleteUser(1);

            //Then
            assertEquals(personaEsperada, personaObtenida);
        }
    }

    @Test
    @DisplayName("Test para comprobar la actualización de persona.")
    void updatePersona() {
        //Given
        PersonaDtoGet personaEsperada = new PersonaDtoGet(1, "Tiburcio", "1234",
                "Manolo", "Perez", "manolo@gmail.com", "roblox@gmail.com",
                "Albacete", true, LocalDate.parse("2023-02-01"),
                "www.google.com", LocalDate.parse("2023-08-01"));

        PersonaDtoPost personaInsertada = new PersonaDtoPost("Tiburcio", "1234", "Manolo",
                "Perez", "manolo@gmail.com", "roblox@gmail.com",
                "Albacete", true, LocalDate.parse("2023-02-01"),
                "www.google.com", LocalDate.parse("2023-08-01"));

        Persona persona = new Persona(1, "Eusebio", "1234",
                "Carlos", "Perez", "juanCarlos@gmail.com",
                "juanCarlos@gmail.com", "La ciudad no puede estar vacía", false,
                LocalDate.parse("2023-02-01"), "Hola", LocalDate.parse("2023-08-01"));

        Persona personaActualizada = new Persona(1, "Tiburcio", "1234", "Manolo",
                "Perez", "manolo@gmail.com", "roblox@gmail.com",
                "Albacete", true, LocalDate.parse("2023-02-01"),
                "www.google.com", LocalDate.parse("2023-08-01"));

        when(personaRepository.findById(1)).thenReturn(Optional.of(persona));
        when(personaRepository.save(personaActualizada)).thenReturn(personaActualizada);

        //When
        PersonaDtoGet personaObtenida = personaService.updatePersona(1, personaInsertada);

        //Then
        assertEquals(personaEsperada, personaObtenida);
    }
}

