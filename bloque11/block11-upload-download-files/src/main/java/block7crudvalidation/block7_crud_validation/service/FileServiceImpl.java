package block7crudvalidation.block7_crud_validation.service;

import block7crudvalidation.block7_crud_validation.converters.FileMapper;
import block7crudvalidation.block7_crud_validation.dtos.file.FileDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.file.FileDtoPost;
import block7crudvalidation.block7_crud_validation.error.EntityNotFoundException;
import block7crudvalidation.block7_crud_validation.error.UnprocessableEntityException;
import block7crudvalidation.block7_crud_validation.repository.FileRepository;
import block7crudvalidation.block7_crud_validation.service.interfaces.FileService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import block7crudvalidation.block7_crud_validation.domain.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    FileMapper fileMapper = Mappers.getMapper(FileMapper.class);

    String path;

    @Override
    public FileDtoGet addFile(MultipartFile document, String categoria) throws IOException {

        String fileName = StringUtils.cleanPath(document.getName());
        Path filePath = Paths.get(path).resolve(fileName);
        Files.copy(document.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        System.out.println(document.getContentType());

        if (!document.getContentType().equals(categoria)) {
            throw new UnprocessableEntityException("La categoría del archivo debe coincidir con la especificada.");
        }

        File file = fileMapper.fileDtoPostToFile(new FileDtoPost(document.getName(), LocalDate.now(), document.getContentType()));
        file.setPath(path);

        return fileMapper.fileToFileDtoGet(fileRepository.save(file));
    }

    @Override
    public String setPath(String newPath) {
        path = newPath;
        return path;
    }

    @Override
    public Resource findFileById(Integer id) throws
            EntityNotFoundException {
        File file = fileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El fichero con id: "
                + id + " no existe."));

        Path filePath = Paths.get(path).resolve(file.getNombreFichero());
        return new FileSystemResource(filePath.toFile());
    }

    @Override
    public Resource findFileByName(String name) throws EntityNotFoundException {
        File file = fileRepository.findByName(name);

        Path filePath = Paths.get(path).resolve(file.getNombreFichero());
        return new FileSystemResource(filePath.toFile());
    }
}
