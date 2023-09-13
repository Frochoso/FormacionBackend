package block7crudvalidation.block7_crud_validation.authentication;

import block7crudvalidation.block7_crud_validation.domain.Persona;
import block7crudvalidation.block7_crud_validation.error.EntityNotFoundException;
import block7crudvalidation.block7_crud_validation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    JwtToken jwtToken;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMap) {
        String username = requestMap.get("username");
        String password = requestMap.get("password");

        Persona persona = personaRepository.findByUsuario(username).stream().findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Esa persona no existe."));

        if (!persona.getPassword().equals(password)) {
            throw new EntityNotFoundException("Contraseña incorrecta.");
        }

        String role = Boolean.TRUE.equals(persona.getAdmin()) ? "ROLE_ADMIN" : "ROLE_USER";
        return new ResponseEntity<>(jwtToken.generateToken(username, role), HttpStatus.OK);
    }
}
