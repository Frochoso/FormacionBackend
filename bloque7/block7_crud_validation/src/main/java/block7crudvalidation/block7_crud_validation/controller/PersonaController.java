package block7crudvalidation.block7_crud_validation.controller;

import block7crudvalidation.block7_crud_validation.dtos.PersonaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.PersonaDtoPost;
import block7crudvalidation.block7_crud_validation.service.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controller")
public class PersonaController {

    @Autowired
    PersonaServiceImpl personaService;

    @PostMapping(value = "/addPerson", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonaDtoGet addUser(@RequestBody PersonaDtoPost personaDtoPost) throws Exception {

        if (personaDtoPost.getUsuario() == null) {
            throw new Exception("Usuario no puede ser nulo");
        }
        if (personaDtoPost.getUsuario().length() < 6) {
            throw new Exception("La longitud mínima es de 6");
        }
        if (personaDtoPost.getUsuario().length() > 10) {
            throw new Exception("La longitud máxima es de 10");
        }
        if (personaDtoPost.getPassword() == null) {
            throw new Exception("La contraseña no puede ser nula");
        }
        if (personaDtoPost.getName() == null) {
            throw new Exception("El nombre no puede estar vacío");
        }
        if (personaDtoPost.getSurname() == null) {
            throw new Exception("El apellido no puede estar vacío");
        }
        if (personaDtoPost.getCompanyEmail() == null) {
            throw new Exception("El email corporativo no puede estar vacío");
        }
        if (personaDtoPost.getPersonalEmail() == null) {
            throw new Exception("El email personal no puede estar vacío");
        }
        if (personaDtoPost.getCity() == null) {
            throw new Exception("La ciudad no puede estar vacía");
        }
        if (personaDtoPost.getCreatedDate() == null) {
            throw new Exception("La fecha de creación no puede ser nula");
        }
        if (personaDtoPost.getImagenUrl() == null) {
            throw new Exception("La url de la imagen debe ser especificada");
        }
        if (personaDtoPost.getTerminationDate() == null) {
            throw new Exception("La fecha de finiquito no puede ser nula");
        }
        return personaService.addUser(personaDtoPost);
    }

    @GetMapping(value = "/{id}")
    public PersonaDtoGet findPersonaById(@PathVariable Integer id) {
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
