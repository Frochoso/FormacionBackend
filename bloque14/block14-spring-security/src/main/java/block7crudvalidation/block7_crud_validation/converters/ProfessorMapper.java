package block7crudvalidation.block7_crud_validation.converters;

import block7crudvalidation.block7_crud_validation.domain.Persona;
import block7crudvalidation.block7_crud_validation.domain.Professor;
import block7crudvalidation.block7_crud_validation.dtos.professor.FullProfessorDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.professor.ProfessorDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.professor.ProfessorDtoPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ProfessorMapper {

    ProfessorDtoGet professorDtoPostToProfessorDtoGet(ProfessorDtoPost professorDtoPost);

    @Mapping(target = "branch", source = "branch")
    @Mapping(target = "comments", source = "comments")
    Professor professorDtoPostToProfessor(ProfessorDtoPost professorDtoPost);

    @Mapping(target = "idProfessor", source = "idProfesor")
    @Mapping(target = "idPersona", source = "persona.idPersona")
    @Mapping(target = "comments", source = "comments")
    @Mapping(target = "branch", source = "branch")
    ProfessorDtoGet professorToProfessorDtoGet(Professor professor);

    @Mapping(target = "idProfesor", source = "idProfessor")
    @Mapping(target = "persona.idPersona", source = "idPersona")
    @Mapping(target = "comments", source = "comments")
    @Mapping(target = "branch", source = "branch")
    Professor professorDtoGetToProfessor(ProfessorDtoGet professorDtoGet);

    List<ProfessorDtoGet> professorToProfessorDtoGet(List<Professor> professor);

    @Mapping(target = "idPersona", source = "persona.idPersona")
    @Mapping(target = "idProfessor", source = "professor.idProfesor")
    FullProfessorDtoGet createNewFullProfessorDtoGet(Professor professor, Persona persona);


}
