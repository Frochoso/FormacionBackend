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

import java.time.LocalDate;
import java.util.HashMap;
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
    public ProfessorDtoGet getProfessor(@PathVariable Integer id, Optional<OutputType> outputType) throws EntityNotFoundException {
        return myFeign.getProfessor(id, outputType);
    }

    @GetMapping(value = "/filters")
    public List<PersonaDtoGet> findByFilters(@PathVariable(value = "user", required = false) String user
            , @PathVariable(value = "name", required = false) String name
            , @PathVariable(value = "surname", required = false) String surname
            , @PathVariable(value = "fechaDesde", required = false) LocalDate fechaDesde
            , @PathVariable(value = "fechaHasta", required = false) LocalDate fechaHasta
            , @PathVariable(value = "ordenar", required = false) String ordenar
            , @PathVariable(value = "pagina", required = false) Integer pagina
            , @PathVariable(value = "tamanoPagina", required = false) Integer tamanoPagina) {

        HashMap<String, Object> data = new HashMap<>();
        data.put("usuario", user);
        data.put("name", name);
        data.put("surname", surname);
        data.put("created_date", fechaDesde);
        data.put("termination_date", fechaHasta);
        data.put("ordenar", ordenar);

        return personaService.findByFilters(data, pagina, tamanoPagina);
    }

}
