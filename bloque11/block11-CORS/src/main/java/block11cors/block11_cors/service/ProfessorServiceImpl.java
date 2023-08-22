package block11cors.block11_cors.service;

import block11cors.block11_cors.converters.ProfessorMapper;
import block11cors.block11_cors.dtos.professor.ProfessorDtoGet;
import block11cors.block11_cors.dtos.professor.ProfessorDtoPost;
import block11cors.block11_cors.enumerations.OutputType;
import block11cors.block11_cors.error.EntityNotFoundException;
import block11cors.block11_cors.error.UnprocessableEntityException;
import block11cors.block11_cors.repository.PersonaRepository;
import block11cors.block11_cors.repository.ProfessorRepository;
import block11cors.block11_cors.service.interfaces.ProfessorService;
import block11cors.block11_cors.domain.Persona;
import block11cors.block11_cors.domain.Professor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    ProfessorMapper mapper = Mappers.getMapper(ProfessorMapper.class);

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public ProfessorDtoGet addProfessor(ProfessorDtoPost professorDtoPost) {

        if (professorDtoPost.getIdPersona() == null) {
            throw new UnprocessableEntityException("El id de persona no puede ser nulo.");
        }

        Persona persona = personaRepository.findById(professorDtoPost.getIdPersona()).orElseThrow(() -> new EntityNotFoundException("Persona con id: " + professorDtoPost.getIdPersona() + " no existe."));

        Professor professor = mapper.professorDtoPostToProfessor(professorDtoPost);
        professor.setPersona(persona);

        return mapper.professorToProfessorDtoGet(professorRepository.save(professor));
    }

    @Override
    public ProfessorDtoGet updateProfessor(Integer id, ProfessorDtoPost professorDtoPost) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Professor con id: " + id + " no existe."));

        professor.setBranch(professorDtoPost.getBranch());
        professor.setComments(professorDtoPost.getComments());
        professor.setPersona(personaRepository.findById(professorDtoPost.getIdPersona()).orElseThrow(() -> new EntityNotFoundException("Persona con id: " + id + " no existe.")));

        professorRepository.save(professor);

        return mapper.professorToProfessorDtoGet(professor);
    }

    @Override
    public ProfessorDtoGet deleteProfessor(Integer id) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Professor con id: " + id + " no existe."));
        professorRepository.delete(professor);
        return mapper.professorToProfessorDtoGet(professor);
    }

    @Override
    public ProfessorDtoGet getProfessor(Integer id, Optional<OutputType> outputType) {

        Professor professor = professorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Professor con id: " + id + " no existe."));
        OutputType type = outputType.orElse(OutputType.simple);

        ProfessorDtoGet dtoDevuelto = new ProfessorDtoGet();

        switch (type) {
            case simple:
                dtoDevuelto = mapper.professorToProfessorDtoGet(professor);
                break;
            case full:
                dtoDevuelto = mapper.createNewFullProfessorDtoGet(professor, professor.getPersona());
                break;
        }
        return dtoDevuelto;
    }

    @Override
    public List<ProfessorDtoGet> findProfessorByName(String name, Optional<OutputType> outputType) {

        OutputType type = outputType.orElse(OutputType.simple);

        List<Professor> professorList = professorRepository.findByName(name);
        List<ProfessorDtoGet> dtoDevuelto = new ArrayList<>();

        switch (type) {
            case simple:
                dtoDevuelto = mapper.professorToProfessorDtoGet(professorList);
                break;
            case full:
                for (Professor professor : professorList) {
                    dtoDevuelto.add(mapper.createNewFullProfessorDtoGet(professor, professor.getPersona()));
                }
                break;
        }
        return dtoDevuelto;
    }

    @Override
    public List<ProfessorDtoGet> findAllProfessors(Optional<OutputType> outputType) {

        OutputType type = outputType.orElse(OutputType.simple);

        List<Professor> professorList = professorRepository.findAll();
        List<ProfessorDtoGet> dtoDevuelto = new ArrayList<>();

        switch (type) {
            case simple:
                dtoDevuelto = mapper.professorToProfessorDtoGet(professorList);
                break;
            case full:
                for (Professor professor : professorList) {
                    dtoDevuelto.add(mapper.createNewFullProfessorDtoGet(professor, professor.getPersona()));
                }
                break;
        }
        return dtoDevuelto;
    }
}
