package block7crudvalidation.block7_crud_validation.service;

import block7crudvalidation.block7_crud_validation.dtos.PersonaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.PersonaDtoPost;
import block7crudvalidation.block7_crud_validation.repository.PersonaRepository;
import block7crudvalidation.block7_crud_validation.converters.PersonaMapper;
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
    public PersonaDtoGet findById(Integer id) {
        return mapper.convertPersonaToPersonaDtoGet(personaRepository.findById(id).stream().findFirst().orElse(null));
    }

    @Override
    public List<PersonaDtoGet> findByUsuario(String usuario) {
        return mapper.convertListPersonaToListPersonaDtoGet(personaRepository.findByUsuario(usuario));
    }

    @Override
    public List<PersonaDtoGet> findAll() {
        return mapper.convertListPersonaToListPersonaDtoGet(personaRepository.findAll());
    }

    @Override
    public PersonaDtoGet addUser(PersonaDtoPost personaDtoPost) {
        return mapper.convertPersonaToPersonaDtoGet(personaRepository.save(mapper.convertPersonaDtoPostToPersona(personaDtoPost)));
    }
}
