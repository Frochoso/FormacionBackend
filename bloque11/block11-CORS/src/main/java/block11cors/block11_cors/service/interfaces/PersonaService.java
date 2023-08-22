package block11cors.block11_cors.service.interfaces;

import block11cors.block11_cors.dtos.persona.PersonaDtoGet;
import block11cors.block11_cors.dtos.persona.PersonaDtoPost;
import block11cors.block11_cors.error.UnprocessableEntityException;
import jakarta.transaction.Transactional;

import java.util.List;

public interface PersonaService {

    PersonaDtoGet findById(Integer id);

    List<PersonaDtoGet> findByUsuario(String usuario);

    List<PersonaDtoGet> findAll();

    @Transactional
    PersonaDtoGet addUser(PersonaDtoPost personaDtoPost) throws UnprocessableEntityException;

    @Transactional
    PersonaDtoGet deleteUser(Integer id);

    @Transactional
    PersonaDtoGet updatePersona(Integer id, PersonaDtoPost personaDtoPost);
}
