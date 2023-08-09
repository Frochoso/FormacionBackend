package block7_crud.block7_crud.service;

import block7_crud.block7_crud.converters.PersonaMapper;
import block7_crud.block7_crud.dtos.PersonaDtoGet;
import block7_crud.block7_crud.dtos.PersonaDtoPost;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface PersonaService {

    PersonaDtoGet addPerson(PersonaDtoPost personaDtoPost);

    PersonaDtoGet getPersonById(int id);

    List<PersonaDtoGet> findPeopleByNombre(String nombre);

    PersonaDtoGet deletePersonById(int id);

    PersonaDtoGet updatePerson(Integer id, PersonaDtoPost personaDtoPost);

    List<PersonaDtoGet> findAllPeople();


}
