package block7crudvalidation.block7_crud_validation.auxiliares;

import block7crudvalidation.block7_crud_validation.dtos.professor.ProfessorDtoGet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuxiliarProfessorDtoGet {

    public static void compararListaProfessorDtoGet(List<ProfessorDtoGet> listaEsperada, List<ProfessorDtoGet> listaObtenida) {

        for (ProfessorDtoGet esperado : listaEsperada) {
            listaObtenida.stream().forEach(obtenida -> {

                assertEquals(esperado.getIdPersona(), obtenida.getIdPersona());
                assertEquals(esperado.getIdProfessor(), obtenida.getIdProfessor());
                assertEquals(esperado.getBranch(), obtenida.getBranch());
                assertEquals(esperado.getComments(), obtenida.getComments());
            });
        }
    }
}
