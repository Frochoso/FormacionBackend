package com.block6_person_controllers.block6_person_controllers.service;

import com.block6_person_controllers.block6_person_controllers.domain.Ciudad;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiudadService {

    private List<Ciudad> listasCiudades = new ArrayList<>();

    public Ciudad addCiudad(Ciudad ciudad) {
        listasCiudades.add(ciudad);
        return ciudad;
    }

    public List<Ciudad> getCiudades() {
        return listasCiudades;
    }

}
