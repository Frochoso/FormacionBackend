package block7crudvalidation.block7_crud_validation.service;

import block7crudvalidation.block7_crud_validation.dtos.PersonaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.PersonaDtoPost;

import java.util.List;

public interface PersonaService {

    PersonaDtoGet findById(Integer id);

    List<PersonaDtoGet> findByUsuario(String usuario);

    List<PersonaDtoGet> findAll();

    PersonaDtoGet addUser (PersonaDtoPost personaDtoPost);
}
