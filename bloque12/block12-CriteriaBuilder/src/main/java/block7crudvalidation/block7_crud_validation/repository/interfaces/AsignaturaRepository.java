package block7crudvalidation.block7_crud_validation.repository.interfaces;

import block7crudvalidation.block7_crud_validation.domain.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {
    Asignatura findAsignaturas(Integer idAsignatura);
}
