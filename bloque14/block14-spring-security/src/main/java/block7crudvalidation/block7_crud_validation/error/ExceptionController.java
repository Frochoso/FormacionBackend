package block7crudvalidation.block7_crud_validation.error;

import org.junit.Ignore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ControllerAdvice
@ResponseBody
public class ExceptionController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public static CustomError handleEntityNotFoundException(EntityNotFoundException exception) {
        return new CustomError(LocalDate.now(), HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public static CustomError handleUnproccesableEntityException(UnprocessableEntityException exception) {
        return new CustomError(LocalDate.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), exception.getMessage());
    }

}
