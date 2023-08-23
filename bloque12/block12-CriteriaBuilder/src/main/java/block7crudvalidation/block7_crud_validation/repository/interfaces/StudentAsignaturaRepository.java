package block7crudvalidation.block7_crud_validation.repository.interfaces;

import block7crudvalidation.block7_crud_validation.domain.Asignatura;
import block7crudvalidation.block7_crud_validation.domain.Student;
import block7crudvalidation.block7_crud_validation.domain.StudentAsignaturas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAsignaturaRepository extends JpaRepository<StudentAsignaturas, Integer> {

    List<Asignatura> findAsignaturasByStudentId(Integer idStudent);

    Student findStudentById(@Param("idStudent") Integer idStudent);

}
