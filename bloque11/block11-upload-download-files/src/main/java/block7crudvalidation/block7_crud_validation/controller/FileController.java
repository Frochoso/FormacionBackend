package block7crudvalidation.block7_crud_validation.controller;

import block7crudvalidation.block7_crud_validation.dtos.file.FileDtoGet;
import block7crudvalidation.block7_crud_validation.service.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController()
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileServiceImpl fileService;

    @PostMapping("/addfile")
    public FileDtoGet addFile(@RequestParam("document") MultipartFile document,
                              @RequestParam("categoria") String categoria) throws IOException {
        return fileService.addFile(document, categoria);
    }

    @GetMapping("/setpath")
    public String setPath(@RequestParam("path") String newPath) {
        return fileService.setPath(newPath);
    }

    @GetMapping("/findById/{id}")
    public Resource findById(@PathVariable Integer id) {
        return fileService.findFileById(id);
    }

    @GetMapping("/findByName/{name}")
    public Resource findById(@PathVariable String name) {
        return fileService.findFileByName(name);
    }
}