package block7crudvalidation.block7_crud_validation.controller;

import block7crudvalidation.block7_crud_validation.domain.Persona;
import block7crudvalidation.block7_crud_validation.dtos.PersonaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.PersonaDtoPost;
import block7crudvalidation.block7_crud_validation.error.CustomError;
import block7crudvalidation.block7_crud_validation.error.EntityNotFoundException;
import block7crudvalidation.block7_crud_validation.error.ExceptionController;
import block7crudvalidation.block7_crud_validation.error.UnprocessableEntityException;
import block7crudvalidation.block7_crud_validation.service.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/controller")
public class PersonaController {

    @Autowired
    PersonaServiceImpl personaService;

    @PostMapping(value = "/addPerson", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public PersonaDtoGet addUser(@RequestBody PersonaDtoPost personaDtoPost) throws UnprocessableEntityException {
        return personaService.addUser(personaDtoPost);
    }

    @DeleteMapping(value = "/deletePerson/{id}")
    public PersonaDtoGet deleteUser(@PathVariable Integer id) {
        return personaService.deleteUser(id);
    }

    @PutMapping(value = "/updatePerson/{id}")
    public PersonaDtoGet updatePerson(@PathVariable Integer id, @RequestBody PersonaDtoPost personaDtoPost) {
        return personaService.updatePersona(id, personaDtoPost);
    }

    @GetMapping(value = "/{id}")
    public PersonaDtoGet findPersonaById(@PathVariable Integer id) throws EntityNotFoundException {
        return personaService.findById(id);
    }

    @GetMapping()
    public List<PersonaDtoGet> findAllPeople() {
        return personaService.findAll();
    }

    @GetMapping(value = "findUser/{usuario}")
    public List<PersonaDtoGet> findPersonaByUsuario(@PathVariable String usuario) {
        return personaService.findByUsuario(usuario);
    }


}
