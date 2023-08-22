package block11cors.block11_cors.repository;

import block11cors.block11_cors.domain.StudentAsignaturas;
import block11cors.block11_cors.domain.Asignatura;
import block11cors.block11_cors.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAsignaturaRepository extends JpaRepository<StudentAsignaturas, Integer> {

    List<Asignatura> findAsignaturasByStudentId(Integer idStudent);

    Student findStudentById(@Param("idStudent") Integer idStudent);

}
