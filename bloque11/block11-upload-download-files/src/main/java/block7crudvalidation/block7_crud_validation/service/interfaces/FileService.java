package block7crudvalidation.block7_crud_validation.service.interfaces;

import block7crudvalidation.block7_crud_validation.dtos.file.FileDtoGet;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    FileDtoGet addFile(MultipartFile document, String categoria) throws IOException;

    String setPath(String path);

    Resource findFileById(Integer id);

    Resource findFileByName(String name);
}
