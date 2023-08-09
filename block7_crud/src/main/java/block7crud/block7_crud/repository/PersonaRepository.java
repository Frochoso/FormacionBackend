package block7crud.block7_crud.repository;

import block7crud.block7_crud.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    

}

