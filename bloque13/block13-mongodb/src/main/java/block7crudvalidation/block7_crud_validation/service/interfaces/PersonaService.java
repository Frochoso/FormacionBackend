package block7crudvalidation.block7_crud_validation.service.interfaces;

import block7crudvalidation.block7_crud_validation.dtos.persona.PersonaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.persona.PersonaDtoPost;
import block7crudvalidation.block7_crud_validation.error.UnprocessableEntityException;

import java.util.HashMap;
import java.util.List;

public interface PersonaService {

    PersonaDtoGet findById(String id);

    PersonaDtoGet addUser(PersonaDtoPost personaDtoPost) throws UnprocessableEntityException;

    PersonaDtoGet deleteUser(String id);

    PersonaDtoGet updatePersona(String id, PersonaDtoPost personaDtoPost);

    List<PersonaDtoGet> findAll(Integer page, Integer size);
}
