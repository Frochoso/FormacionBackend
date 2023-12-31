package block11cors.block11_cors.repository;

import block11cors.block11_cors.domain.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {
    Asignatura findAsignaturas(Integer idAsignatura);
}
