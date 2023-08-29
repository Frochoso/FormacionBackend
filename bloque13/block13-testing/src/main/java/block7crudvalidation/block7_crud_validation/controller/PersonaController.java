package block7crudvalidation.block7_crud_validation.controller;

import block7crudvalidation.block7_crud_validation.dtos.persona.PersonaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.persona.PersonaDtoPost;
import block7crudvalidation.block7_crud_validation.dtos.professor.ProfessorDtoGet;
import block7crudvalidation.block7_crud_validation.enumerations.OutputType;
import block7crudvalidation.block7_crud_validation.error.EntityNotFoundException;
import block7crudvalidation.block7_crud_validation.error.UnprocessableEntityException;
import block7crudvalidation.block7_crud_validation.feign.MyFeign;
import block7crudvalidation.block7_crud_validation.service.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/controller")
public class PersonaController {

    @Autowired
    PersonaServiceImpl personaService;

    @Autowired
    MyFeign myFeign;

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

    @GetMapping(value = "/getProfessor/{id}")
    ProfessorDtoGet getProfessor(@PathVariable("id") Integer id, @RequestParam Optional<OutputType> outputType)
            throws EntityNotFoundException {
        return myFeign.getProfessor(id, outputType);
    }
}
