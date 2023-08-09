package block7crud.block7_crud.dtos.student;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoPost {
    int id;
    String name;
    String lastName;
}

