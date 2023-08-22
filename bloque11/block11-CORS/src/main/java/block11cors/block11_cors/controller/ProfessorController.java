package block11cors.block11_cors.controller;

import block11cors.block11_cors.dtos.professor.ProfessorDtoGet;
import block11cors.block11_cors.dtos.professor.ProfessorDtoPost;
import block11cors.block11_cors.enumerations.OutputType;
import block11cors.block11_cors.error.EntityNotFoundException;
import block11cors.block11_cors.service.ProfessorServiceImpl;
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
