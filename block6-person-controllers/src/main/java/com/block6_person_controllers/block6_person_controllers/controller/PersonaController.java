package com.block6_person_controllers.block6_person_controllers.controller;

import com.block6_person_controllers.block6_person_controllers.domain.Persona;
import com.block6_person_controllers.block6_person_controllers.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlador1")
public class PersonaController {

    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping(value = "/addPersona")
    public ResponseEntity<Persona> nuevaPersona(@RequestHeader("nombre") String nombre, @RequestHeader("poblacion") String poblacion, @RequestHeader("edad") Integer edad) {
        return new ResponseEntity<>(personaService.getPersona(nombre, poblacion, edad), HttpStatus.CREATED);
    }

    @GetMapping("/bean/{bean}")
    public ResponseEntity<Persona> getPersonaByBean(@PathVariable String bean) {
        return personaService.obtenerPersona(bean);
    }
}
