package block7crudvalidation.block7_crud_validation.service;

import block7crudvalidation.block7_crud_validation.converters.ProfessorMapper;
import block7crudvalidation.block7_crud_validation.domain.Persona;
import block7crudvalidation.block7_crud_validation.domain.Professor;
import block7crudvalidation.block7_crud_validation.dtos.professor.ProfessorDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.professor.ProfessorDtoPost;
import block7crudvalidation.block7_crud_validation.enumerations.OutputType;
import block7crudvalidation.block7_crud_validation.error.EntityNotFoundException;
import block7crudvalidation.block7_crud_validation.error.UnprocessableEntityException;
import block7crudvalidation.block7_crud_validation.repository.interfaces.PersonaRepository;
import block7crudvalidation.block7_crud_validation.repository.interfaces.ProfessorRepository;
import block7crudvalidation.block7_crud_validation.service.interfaces.ProfessorService;
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
