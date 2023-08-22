package block11cors.block11_cors.repository;

import block11cors.block11_cors.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    List<Professor> findByName(String name);

}
