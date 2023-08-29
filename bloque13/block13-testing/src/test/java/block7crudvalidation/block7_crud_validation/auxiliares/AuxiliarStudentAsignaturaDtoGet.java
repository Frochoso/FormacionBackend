package block7crudvalidation.block7_crud_validation.auxiliares;

import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDtoGet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuxiliarStudentAsignaturaDtoGet {

    public static void compararListasAuxiliarStudentAsignaturaDtoGet(List<StudentAsignaturaDtoGet> listaEsperada,
                                                                     List<StudentAsignaturaDtoGet> listaObtenida) {
        for (StudentAsignaturaDtoGet esperado : listaEsperada) {
            listaObtenida.forEach(obtenido -> {
                assertEquals(esperado.getStudent(), obtenido.getStudent());
                assertEquals(esperado.getAsignaturaDtoGetSet(), obtenido.getAsignaturaDtoGetSet());
            });
        }
    }

}
