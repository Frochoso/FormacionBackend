package block11cors.block11_cors.error;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class CustomError{

    private LocalDate timeStamp;
    private Integer httpCode;
    private String mensaje;

}
