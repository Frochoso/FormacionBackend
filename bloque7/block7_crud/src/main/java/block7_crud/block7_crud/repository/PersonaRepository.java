package block7_crud.block7_crud.repository;

import block7_crud.block7_crud.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    Optional<List<Persona>> findPeopleByNombre(String nombre);

}
