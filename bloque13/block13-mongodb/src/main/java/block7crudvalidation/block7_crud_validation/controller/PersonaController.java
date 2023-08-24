package block7crudvalidation.block7_crud_validation.controller;

import block7crudvalidation.block7_crud_validation.dtos.persona.PersonaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.persona.PersonaDtoPost;
import block7crudvalidation.block7_crud_validation.error.EntityNotFoundException;
import block7crudvalidation.block7_crud_validation.service.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controller")
public class PersonaController {

    @Autowired
    PersonaServiceImpl personaService;


    @PostMapping(value = "/addPerson")
    public PersonaDtoGet addUser(@RequestBody PersonaDtoPost personaDtoPost) {
        return personaService.addUser(personaDtoPost);
    }

    @DeleteMapping(value = "/deletePerson/{id}")
    public PersonaDtoGet deleteUser(@PathVariable String id) {
        return personaService.deleteUser(id);
    }

    @PutMapping(value = "/updatePerson/{id}")
    public PersonaDtoGet updatePerson(@PathVariable String id, @RequestBody PersonaDtoPost personaDtoPost) {
        return personaService.updatePersona(id, personaDtoPost);
    }

    @GetMapping(value = "/{id}")
    public PersonaDtoGet findPersonaById(@PathVariable String id) throws EntityNotFoundException {
        return personaService.findById(id);
    }

    @GetMapping
    public List<PersonaDtoGet> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        return personaService.findAll(page, size);
    }

}
