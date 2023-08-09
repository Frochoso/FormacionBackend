package block7_crud.block7_crud.controller;

import block7_crud.block7_crud.dtos.PersonaDtoGet;
import block7_crud.block7_crud.dtos.PersonaDtoPost;
import block7_crud.block7_crud.service.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaServiceImpl personaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonaDtoGet> addPerson(@RequestBody PersonaDtoPost personaDtoPost) {
        return ResponseEntity.created(URI.create("/persona")).body(personaService.addPerson(personaDtoPost));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PersonaDtoGet> updatePerson(@PathVariable Integer id, @RequestBody PersonaDtoPost personaDtoPost) {
        try {
            return ResponseEntity.ok().body(personaService.updatePerson(id, personaDtoPost));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PersonaDtoGet> deletePerson(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(personaService.deletePersonById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/person/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PersonaDtoGet> getPerson(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(personaService.getPersonById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/people/{nombre}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PersonaDtoGet>> findPeopleByNombre(@PathVariable String nombre) {
        try {
            return ResponseEntity.ok().body(personaService.findPeopleByNombre(nombre));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PersonaDtoGet>> findAllPeople() {
        try {
            return ResponseEntity.ok().body(personaService.findAllPeople());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



}
