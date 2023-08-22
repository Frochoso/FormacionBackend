package block7crudvalidation.block7_crud_validation.feign;

import block7crudvalidation.block7_crud_validation.dtos.professor.ProfessorDtoGet;
import block7crudvalidation.block7_crud_validation.enumerations.OutputType;
import block7crudvalidation.block7_crud_validation.error.EntityNotFoundException;
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
