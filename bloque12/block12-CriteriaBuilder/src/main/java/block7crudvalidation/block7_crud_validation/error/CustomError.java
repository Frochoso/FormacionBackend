package block7crudvalidation.block7_crud_validation.error;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class CustomError{

    private LocalDate timeStamp;
    private Integer httpCode;
    private String mensaje;

}
