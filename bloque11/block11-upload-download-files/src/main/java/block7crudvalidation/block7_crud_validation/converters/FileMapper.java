package block7crudvalidation.block7_crud_validation.converters;

import block7crudvalidation.block7_crud_validation.dtos.file.FileDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.file.FileDtoPost;
import org.mapstruct.Mapper;
import block7crudvalidation.block7_crud_validation.domain.File;
import org.mapstruct.Mapping;

@Mapper
public interface FileMapper {

    @Mapping(target = "nombreFichero", source = "nombreFichero")
    @Mapping(target = "fechaSubida", source = "fechaSubida")
    @Mapping(target = "categoria", source = "categoria")
    File fileDtoPostToFile(FileDtoPost fileDtoPost);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombreFichero", source = "nombreFichero")
    @Mapping(target = "fechaSubida", source = "fechaSubida")
    @Mapping(target = "categoria", source = "categoria")
    FileDtoGet fileToFileDtoGet(File file);

}
