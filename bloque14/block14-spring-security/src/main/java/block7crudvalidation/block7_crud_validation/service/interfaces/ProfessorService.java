package block7crudvalidation.block7_crud_validation.service.interfaces;


import block7crudvalidation.block7_crud_validation.dtos.professor.ProfessorDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.professor.ProfessorDtoPost;
import block7crudvalidation.block7_crud_validation.enumerations.OutputType;

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
