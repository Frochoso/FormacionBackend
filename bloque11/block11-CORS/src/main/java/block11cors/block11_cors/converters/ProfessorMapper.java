package block11cors.block11_cors.converters;

import block11cors.block11_cors.dtos.professor.FullProfessorDtoGet;
import block11cors.block11_cors.dtos.professor.ProfessorDtoGet;
import block11cors.block11_cors.dtos.professor.ProfessorDtoPost;
import block11cors.block11_cors.domain.Persona;
import block11cors.block11_cors.domain.Professor;
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
    FullProfessorDtoGet createNewFullProfessorDtoGet(Professor professor, Persona persona);


}
