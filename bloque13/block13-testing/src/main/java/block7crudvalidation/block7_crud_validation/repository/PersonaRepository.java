package block7crudvalidation.block7_crud_validation.repository;

import block7crudvalidation.block7_crud_validation.domain.Persona;
import block7crudvalidation.block7_crud_validation.domain.Student;
import block7crudvalidation.block7_crud_validation.dtos.student.StudentDtoGet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    List<Persona> findByUsuario(String usuario);

    Student findStudentAsociado(Integer idPersona);

}
