package com.block6_person_controllers.block6_person_controllers.service;

import com.block6_person_controllers.block6_person_controllers.domain.Persona;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    @Getter
    public Persona personaAlmacenada;


    @Autowired
    @Qualifier("bean1")
    private Persona personaBean1;

    @Autowired
    @Qualifier("bean2")
    private Persona personaBean2;

    @Autowired
    @Qualifier("bean3")
    private Persona personaBean3;

    public Persona getPersona(String nombre, String poblacion, Integer edad) {
        personaAlmacenada=new Persona(nombre, poblacion, edad);
        return personaAlmacenada;
    }

    public ResponseEntity<Persona> obtenerPersona(String bean){
        switch (bean) {
            case "bean1":
                return new ResponseEntity<>(personaBean1, HttpStatus.OK);
            case "bean2":
                return new ResponseEntity<>(personaBean2, HttpStatus.OK);
            case "bean3":
                return new ResponseEntity<>(personaBean3, HttpStatus.OK);
            default:
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
