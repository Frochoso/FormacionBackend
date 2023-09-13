package block7crudvalidation.block7_crud_validation.auxiliares;

import block7crudvalidation.block7_crud_validation.dtos.persona.PersonaDtoGet;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuxiliarPersonaDtoGet {

    public static void compararListaPersonaDtoGet(List<PersonaDtoGet> listaEsperada, List<PersonaDtoGet> listaObtenida) {

        for (PersonaDtoGet esperado : listaEsperada) {
            listaObtenida.stream().forEach(obtenida -> {

                assertEquals(esperado.getIdPersona(), obtenida.getIdPersona());
                assertEquals(esperado.getCompanyEmail(), obtenida.getCompanyEmail());
                assertEquals(esperado.getCity(), obtenida.getCity());
                assertEquals(esperado.getPersonalEmail(), obtenida.getPersonalEmail());
                assertEquals(esperado.getName(), obtenida.getName());
                assertEquals(esperado.getSurname(), obtenida.getSurname());
                assertEquals(esperado.getUsuario(), obtenida.getUsuario());
                assertEquals(esperado.getImagenUrl(), obtenida.getImagenUrl());
                assertEquals(esperado.getPassword(), obtenida.getPassword());
                assertEquals(esperado.getCreatedDate(), obtenida.getCreatedDate());
                assertEquals(esperado.getTerminationDate(), obtenida.getTerminationDate());
            });
        }

    }

}
