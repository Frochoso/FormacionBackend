package block11cors.block11_cors.converters;

import block11cors.block11_cors.dtos.asignatura.AsignaturaDtoGet;
import block11cors.block11_cors.dtos.asignatura.AsignaturaDtoPost;
import block11cors.block11_cors.domain.Asignatura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface AsignaturaMapper {

    @Mapping(target = "students", ignore = true)
    Asignatura asignaturaDtoPostToAsignatura(AsignaturaDtoPost asignaturaDtoPost);

    Asignatura asignaturaDtoGetToAsignatura(AsignaturaDtoGet asignaturaDtoGet);

    @Mapping(target = "idAsignatura", source = "idAsignatura")
    @Mapping(target = "nombreAsignatura", source = "nombreAsignatura")
    @Mapping(target = "comments", source = "comments")
    @Mapping(target = "initialDate", source = "initialDate")
    @Mapping(target = "finishDate", source = "finishDate")
    AsignaturaDtoGet asignaturaToAsignaturaDtoGet(Asignatura asignatura);


    List<AsignaturaDtoGet> asignaturaListToAsignaturaDtoGet(List<Asignatura> asignaturaList);
}
