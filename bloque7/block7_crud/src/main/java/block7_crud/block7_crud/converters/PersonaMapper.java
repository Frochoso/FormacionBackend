package block7_crud.block7_crud.converters;

import block7_crud.block7_crud.domain.Persona;
import block7_crud.block7_crud.dtos.PersonaDtoGet;
import block7_crud.block7_crud.dtos.PersonaDtoPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PersonaMapper {

    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "edad", source = "edad")
    @Mapping(target = "localidad", source = "localidad")
    Persona convertPersonaDtoPostToPersona(PersonaDtoPost personaDtoPost);

    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "edad", source = "edad")
    @Mapping(target = "localidad", source = "localidad")
    @Mapping(target = "id", source = "id")
    Persona convertPersonaDtoGetToPersona(PersonaDtoGet personaDtoGet);

    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "edad", source = "edad")
    @Mapping(target = "localidad", source = "localidad")
    @Mapping(target = "id", source = "id")
    PersonaDtoGet convertPersonaToPersonaDtoGet(Persona persona);

    PersonaDtoPost convertPersonaDtoPostToPersonnaDtoGet(PersonaDtoPost personaDtoPost);


    List<PersonaDtoGet> convertListPersonaToListPersonaDtoGet(List<Persona> personas);
}
