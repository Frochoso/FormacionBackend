package block7crudvalidation.block7_crud_validation.converters;

import block7crudvalidation.block7_crud_validation.domain.Persona;
import block7crudvalidation.block7_crud_validation.dtos.persona.PersonaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.persona.PersonaDtoPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PersonaMapper {

    @Mapping(target = "usuario",source="usuario")
    @Mapping(target = "password",source="password")
    @Mapping(target = "name",source="name")
    @Mapping(target = "surname",source="surname")
    @Mapping(target = "companyEmail",source="companyEmail")
    @Mapping(target = "personalEmail",source="personalEmail")
    @Mapping(target = "city",source="city")
    @Mapping(target = "active",source="active")
    @Mapping(target = "createdDate",source="createdDate")
    @Mapping(target = "imagenUrl",source="imagenUrl")
    @Mapping(target = "terminationDate",source="terminationDate")
    Persona convertPersonaDtoPostToPersona(PersonaDtoPost personaDtoPost);

    Persona convertPersonaDtoGetToPersona(PersonaDtoGet personaDtoGet);

    @Mapping(target = "idPersona",source="idPersona")
    @Mapping(target = "usuario",source="usuario")
    @Mapping(target = "password",source="password")
    @Mapping(target = "name",source="name")
    @Mapping(target = "surname",source="surname")
    @Mapping(target = "companyEmail",source="companyEmail")
    @Mapping(target = "personalEmail",source="personalEmail")
    @Mapping(target = "city",source="city")
    @Mapping(target = "active",source="active")
    @Mapping(target = "createdDate",source="createdDate")
    @Mapping(target = "imagenUrl",source="imagenUrl")
    @Mapping(target = "terminationDate",source="terminationDate")
    PersonaDtoGet convertPersonaToPersonaDtoGet(Persona persona);

    PersonaDtoPost convertPersonaDtoPostToPersonnaDtoGet(PersonaDtoPost personaDtoPost);

    List<PersonaDtoGet> convertListPersonaToListPersonaDtoGet(List<Persona> personas);
}
