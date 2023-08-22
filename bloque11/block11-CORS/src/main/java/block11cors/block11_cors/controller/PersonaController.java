package block11cors.block11_cors.controller;

import block11cors.block11_cors.dtos.persona.PersonaDtoGet;
import block11cors.block11_cors.dtos.persona.PersonaDtoPost;
import block11cors.block11_cors.dtos.professor.ProfessorDtoGet;
import block11cors.block11_cors.enumerations.OutputType;
import block11cors.block11_cors.error.EntityNotFoundException;
import block11cors.block11_cors.error.UnprocessableEntityException;
import block11cors.block11_cors.feign.MyFeign;
import block11cors.block11_cors.service.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class PersonaController {

    @Autowired
    PersonaServiceImpl personaService;

    @Autowired
    MyFeign myFeign;

    @CrossOrigin("https://cdpn.io")
    @PostMapping(value = "/addperson", consumes = MediaType.APPLICATION_JSON_VALUE)
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

    @CrossOrigin("https://cdpn.io")
    @GetMapping("/getall")
    public List<PersonaDtoGet> findAllPeople() {
        return personaService.findAll();
    }

    @GetMapping(value = "findUser/{usuario}")
    public List<PersonaDtoGet> findPersonaByUsuario(@PathVariable String usuario) {
        return personaService.findByUsuario(usuario);
    }

    @GetMapping(value = "/getProfessor/{id}")
    public ProfessorDtoGet getProfessor(@PathVariable Integer id, Optional<OutputType> outputType) throws EntityNotFoundException {
        return myFeign.getProfessor(id, outputType);
    }

}
