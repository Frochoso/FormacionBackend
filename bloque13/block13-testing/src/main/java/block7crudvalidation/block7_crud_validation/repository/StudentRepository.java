package block7crudvalidation.block7_crud_validation.repository;

import block7crudvalidation.block7_crud_validation.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByName(String name);

}
