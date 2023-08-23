package block7crudvalidation.block7_crud_validation.repository.interfaces;

import block7crudvalidation.block7_crud_validation.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    List<Professor> findByName(String name);

}
