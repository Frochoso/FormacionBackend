package block7_crud.block7_crud.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDtoGet {

    private Integer id;
    private String nombre;

    private String edad;

    private String localidad;
}
