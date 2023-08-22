package block7crudvalidation.block7_crud_validation.domain;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student_asignaturas")
@NamedQuery(name = "StudentAsignaturas.findAsignaturasByStudentId", query = "SELECT sa.asignatura FROM StudentAsignaturas sa WHERE sa.student.idStudent = :idStudent")
@NamedQuery(name = "StudentAsignaturas.findStudentById", query = "SELECT sa.student FROM StudentAsignaturas sa WHERE sa.student.idStudent = :idStudent")
public class StudentAsignaturas {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_asignatura")
    private Asignatura asignatura;

    public StudentAsignaturas(Student student, Asignatura asignatura) {
        this.student = student;
        this.asignatura = asignatura;
    }
}
