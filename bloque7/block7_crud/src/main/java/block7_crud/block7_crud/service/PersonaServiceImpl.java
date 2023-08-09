package block7_crud.block7_crud.service;

import block7_crud.block7_crud.converters.PersonaMapper;
import block7_crud.block7_crud.domain.Persona;
import block7_crud.block7_crud.dtos.PersonaDtoGet;
import block7_crud.block7_crud.dtos.PersonaDtoPost;
import block7_crud.block7_crud.repository.PersonaRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public PersonaDtoGet addPerson(PersonaDtoPost personaDtoPost) {
        return mapper.convertPersonaToPersonaDtoGet(personaRepository.save(mapper.convertPersonaDtoPostToPersona(personaDtoPost)));
    }

    @Override
    public PersonaDtoGet getPersonById(int id) {
        return mapper.convertPersonaToPersonaDtoGet(personaRepository.findById(id).stream().findFirst().orElse(null));
    }

    @Override
    public List<PersonaDtoGet> findPeopleByNombre(String nombre) {
        return mapper.convertListPersonaToListPersonaDtoGet(personaRepository.findPeopleByNombre(nombre).stream().findFirst().orElse(null));
    }

    @Override
    public PersonaDtoGet deletePersonById(int id) {
        Persona persona = personaRepository.findById(id).stream().findFirst().orElse(null);
        personaRepository.delete(persona);
        return mapper.convertPersonaToPersonaDtoGet(persona);
    }

    @Override
    public PersonaDtoGet updatePerson(Integer id, PersonaDtoPost student) {
        Persona persona = personaRepository.findById(id).stream().findFirst().orElse(null);
        Persona personaNueva = mapper.convertPersonaDtoPostToPersona(student);

        persona.setEdad(personaNueva.getEdad());
        persona.setLocalidad(personaNueva.getLocalidad());
        persona.setNombre(personaNueva.getNombre());

        return mapper.convertPersonaToPersonaDtoGet(personaRepository.save(persona));
    }

    @Override
    public List<PersonaDtoGet> findAllPeople() {
        return mapper.convertListPersonaToListPersonaDtoGet(personaRepository.findAll());
    }
}
