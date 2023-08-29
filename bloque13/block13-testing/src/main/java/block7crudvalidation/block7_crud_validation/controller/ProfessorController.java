package block7crudvalidation.block7_crud_validation.controller;

import block7crudvalidation.block7_crud_validation.dtos.professor.ProfessorDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.professor.ProfessorDtoPost;
import block7crudvalidation.block7_crud_validation.enumerations.OutputType;
import block7crudvalidation.block7_crud_validation.error.EntityNotFoundException;
import block7crudvalidation.block7_crud_validation.service.ProfessorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorServiceImpl professorService;

    @PostMapping(value = "/addProfessor", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProfessorDtoGet addProfessor(@RequestBody ProfessorDtoPost professorDtoPost) {
        return professorService.addProfessor(professorDtoPost);
    }

    @PutMapping(value = "/updateProfessor/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProfessorDtoGet updateProfessor(@PathVariable Integer id, @RequestBody ProfessorDtoPost professorDtoPost) throws EntityNotFoundException {
        return professorService.updateProfessor(id, professorDtoPost);
    }

    @DeleteMapping(value = "/deleteProfessor/{id}")
    public ProfessorDtoGet deleteProfessor(@PathVariable Integer id) throws EntityNotFoundException {
        return professorService.deleteProfessor(id);
    }

    @GetMapping(value = "/getProfessor/{id}")
    public ProfessorDtoGet getProfessor(@PathVariable Integer id, Optional<OutputType> outputType) throws EntityNotFoundException {
        return professorService.getProfessor(id, outputType);
    }

    @GetMapping(value = "/getProfessor/name/{name}")
    public List<ProfessorDtoGet> getProfessor(@PathVariable String name, Optional<OutputType> outputType) throws EntityNotFoundException {
        return professorService.findProfessorByName(name, outputType);
    }

    @GetMapping()
    public List<ProfessorDtoGet> getProfessor(Optional<OutputType> outputType) throws EntityNotFoundException {
        return professorService.findAllProfessors(outputType);
    }
}
