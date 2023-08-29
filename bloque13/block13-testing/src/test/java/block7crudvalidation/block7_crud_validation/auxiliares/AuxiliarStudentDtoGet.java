package block7crudvalidation.block7_crud_validation.auxiliares;

import block7crudvalidation.block7_crud_validation.dtos.persona.PersonaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.student.StudentDtoGet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuxiliarStudentDtoGet {

    public static void compararListaEstudiantesDtoGet(List<StudentDtoGet> listaEsperada, List<StudentDtoGet> listaObtenida) {

        for (StudentDtoGet esperado : listaEsperada) {
            listaObtenida.stream().forEach(obtenida -> {

                assertEquals(esperado.getId_persona(), obtenida.getId_persona());
                assertEquals(esperado.getIdStudent(), obtenida.getIdStudent());
                assertEquals(esperado.getBranch(), obtenida.getBranch());
                assertEquals(esperado.getComments(), obtenida.getComments());
                assertEquals(esperado.getId_professor(), obtenida.getId_professor());
                assertEquals(esperado.getAsignaturas(), obtenida.getAsignaturas());
                assertEquals(esperado.getNumHoursWeek(), obtenida.getNumHoursWeek());
            });
        }

    }

}
