package block11cors.block11_cors.repository;

import block11cors.block11_cors.domain.Persona;
import block11cors.block11_cors.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    List<Persona> findByUsuario(String usuario);

    Student findStudentAsociado(Integer idPersona);

}
