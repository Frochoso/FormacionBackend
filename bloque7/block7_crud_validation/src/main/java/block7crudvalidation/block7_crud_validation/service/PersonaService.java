package block7crudvalidation.block7_crud_validation.service;

import block7crudvalidation.block7_crud_validation.dtos.PersonaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.PersonaDtoPost;
import block7crudvalidation.block7_crud_validation.error.UnprocessableEntityException;
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
