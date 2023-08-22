package block11cors.block11_cors.service.interfaces;


import block11cors.block11_cors.dtos.professor.ProfessorDtoGet;
import block11cors.block11_cors.dtos.professor.ProfessorDtoPost;
import block11cors.block11_cors.enumerations.OutputType;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {

    ProfessorDtoGet addProfessor(ProfessorDtoPost professorDtoPost);

    ProfessorDtoGet updateProfessor(Integer id, ProfessorDtoPost professorDtoPost);

    ProfessorDtoGet deleteProfessor(Integer id);

    ProfessorDtoGet getProfessor(Integer id, Optional<OutputType> outputType);

    List<ProfessorDtoGet> findProfessorByName(String name, Optional<OutputType> outputType);

    List<ProfessorDtoGet> findAllProfessors(Optional<OutputType> outputType);
}
