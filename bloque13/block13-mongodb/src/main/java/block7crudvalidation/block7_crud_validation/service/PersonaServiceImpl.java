package block7crudvalidation.block7_crud_validation.service;

import block7crudvalidation.block7_crud_validation.domain.Persona;
import block7crudvalidation.block7_crud_validation.dtos.persona.PersonaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.persona.PersonaDtoPost;
import block7crudvalidation.block7_crud_validation.error.UnprocessableEntityException;
import block7crudvalidation.block7_crud_validation.converters.PersonaMapper;
import block7crudvalidation.block7_crud_validation.service.interfaces.PersonaService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonaServiceImpl implements PersonaService {

    PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public PersonaDtoGet findById(String id) {
        Persona persona = mongoTemplate.findById(id, Persona.class);
        return mapper.convertPersonaToPersonaDtoGet(persona);
    }

    @Override
    public PersonaDtoGet addUser(PersonaDtoPost personaDtoPost) {

        if (personaDtoPost.getUsuario() == null) {
            throw new UnprocessableEntityException("El usuario no puede ser nulo");
        }

        if (personaDtoPost.getUsuario().length() < 6) {
            throw new UnprocessableEntityException("La longitud mínima es de 6");
        }

        if (personaDtoPost.getUsuario().length() > 10) {
            throw new UnprocessableEntityException("La longitud máxima es de 10");
        }

        if (personaDtoPost.getPassword() == null) {
            throw new UnprocessableEntityException("La contraseña no puede ser nula");
        }

        if (personaDtoPost.getName() == null) {
            throw new UnprocessableEntityException("El nombre no puede estar vacío");
        }

        if (personaDtoPost.getSurname() == null) {
            throw new UnprocessableEntityException("El apellido no puede estar vacío");
        }

        if (personaDtoPost.getCompanyEmail() == null) {
            throw new UnprocessableEntityException("El email corporativo no puede estar vacío");
        }

        if (personaDtoPost.getPersonalEmail() == null) {
            throw new UnprocessableEntityException("El email personal no puede estar vacío");
        }

        if (personaDtoPost.getCity() == null) {
            throw new UnprocessableEntityException("La ciudad no puede estar vacía");
        }

        if (personaDtoPost.getCreatedDate() == null) {
            throw new UnprocessableEntityException("La fecha de creación no puede ser nula");
        }

        if (personaDtoPost.getImagenUrl() == null) {
            throw new UnprocessableEntityException("La imagen debe ser especificada");
        }

        if (personaDtoPost.getTerminationDate() == null) {
            throw new UnprocessableEntityException("La fecha de finiquito no puede ser nula");
        }

        return mapper.convertPersonaToPersonaDtoGet(mongoTemplate.save(mapper.convertPersonaDtoPostToPersona(personaDtoPost)));
    }

    @Override
    public PersonaDtoGet deleteUser(String id) {

        Persona persona = mongoTemplate.findById(id, Persona.class);
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Persona.class);
        return mapper.convertPersonaToPersonaDtoGet(persona);
    }

    @Override
    public PersonaDtoGet updatePersona(String id, PersonaDtoPost personaDtoPost) {

        Persona persona = mongoTemplate.findById(id, Persona.class);

        persona.setActive(personaDtoPost.isActive());
        persona.setCity(personaDtoPost.getCity());
        persona.setName(personaDtoPost.getName());
        persona.setPassword(personaDtoPost.getPassword());
        persona.setCompanyEmail(personaDtoPost.getCompanyEmail());
        persona.setSurname(personaDtoPost.getSurname());
        persona.setCreatedDate(personaDtoPost.getCreatedDate());
        persona.setPersonalEmail(personaDtoPost.getPersonalEmail());
        persona.setTerminationDate(personaDtoPost.getTerminationDate());
        persona.setImagenUrl(personaDtoPost.getImagenUrl());
        persona.setUsuario(personaDtoPost.getUsuario());

        return mapper.convertPersonaToPersonaDtoGet(mongoTemplate.save(persona));
    }

    @Override
    public List<PersonaDtoGet> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Query query = new Query();
        query.with(pageable);
        return mapper.convertListPersonaToListPersonaDtoGet(mongoTemplate.find(query, Persona.class));
    }
}
