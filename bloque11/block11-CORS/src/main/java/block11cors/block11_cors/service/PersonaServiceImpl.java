package block11cors.block11_cors.service;

import block11cors.block11_cors.converters.PersonaMapper;
import block11cors.block11_cors.dtos.persona.PersonaDtoGet;
import block11cors.block11_cors.dtos.persona.PersonaDtoPost;
import block11cors.block11_cors.error.EntityNotFoundException;
import block11cors.block11_cors.error.UnprocessableEntityException;
import block11cors.block11_cors.repository.PersonaRepository;
import block11cors.block11_cors.service.interfaces.PersonaService;
import block11cors.block11_cors.domain.Persona;
import jakarta.transaction.Transactional;
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
        Persona personaOptional = personaRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Persona con id: " + id + " no encontrada"));
        return mapper.convertPersonaToPersonaDtoGet(personaOptional);
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
    @Transactional
    public PersonaDtoGet addUser(PersonaDtoPost personaDtoPost) throws UnprocessableEntityException {

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

        return mapper.convertPersonaToPersonaDtoGet(personaRepository.save(mapper.convertPersonaDtoPostToPersona(personaDtoPost)));
    }

    @Override
    @Transactional
    public PersonaDtoGet deleteUser(Integer id) {
        if (personaRepository.findStudentAsociado(id) != null) {
            throw new UnprocessableEntityException("No se puede borrar esta persona porque tiene un id de estudiante asociado.");
        }
        Persona persona = personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Persona con id: " + id + " no encontrada"));
        personaRepository.delete(persona);
        return mapper.convertPersonaToPersonaDtoGet(persona);
    }

    @Override
    @Transactional
    public PersonaDtoGet updatePersona(Integer id, PersonaDtoPost personaDtoPost) {

        Persona persona = personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Persona con id: " + id + " no encontrada"));

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

        return mapper.convertPersonaToPersonaDtoGet(personaRepository.save(persona));
    }
}
