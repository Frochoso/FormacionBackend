package block7crudvalidation.block7_crud_validation.dtos.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileDtoGet {

    Integer id;

    String nombreFichero;

    LocalDate fechaSubida;

    String categoria;

    String path;
}
