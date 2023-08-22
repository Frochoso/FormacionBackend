package block11cors.block11_cors.feign;

import block11cors.block11_cors.dtos.professor.ProfessorDtoGet;
import block11cors.block11_cors.enumerations.OutputType;
import block11cors.block11_cors.error.EntityNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(url = "localhost:8081", name = "myFeign")
public interface MyFeign {

    @GetMapping("/professor/getProfessor/{id}")
    ProfessorDtoGet getProfessor(@PathVariable Integer id, Optional<OutputType> outputType)
            throws EntityNotFoundException;

}
