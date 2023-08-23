package block7crudvalidation.block7_crud_validation.converters;

import block7crudvalidation.block7_crud_validation.domain.Asignatura;
import block7crudvalidation.block7_crud_validation.domain.Student;
import block7crudvalidation.block7_crud_validation.domain.StudentAsignaturas;
import block7crudvalidation.block7_crud_validation.dtos.asignatura.AsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.asignatura.AsignaturaDtoPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
