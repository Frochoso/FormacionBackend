package com.block6_person_controllers.block6_person_controllers.controller;

import com.block6_person_controllers.block6_person_controllers.domain.Persona;
import com.block6_person_controllers.block6_person_controllers.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador2")
public class Persona2Controller {

    private final PersonaService personaService;

    @Autowired
    public Persona2Controller(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping(value = "/getPersona")
    public ResponseEntity<Persona> duplicaEdadPersona(Persona persona) {
        return new ResponseEntity<>(new Persona(personaService.getPersonaAlmacenada().getNombre(),
                personaService.getPersonaAlmacenada().getPoblacion(),
                personaService.getPersonaAlmacenada().getEdad() * 2), HttpStatus.CREATED);
    }
}
