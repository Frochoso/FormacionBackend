package block7crudvalidation.block7_crud_validation.domain;

import block7crudvalidation.block7_crud_validation.enumerations.Branch;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
@NamedQuery(name = "Student.findByName", query = " SELECT s FROM Student s WHERE s.persona.name = :name")
public class Student {

    @Id
    @GeneratedValue
    private Integer idStudent;

    @OneToOne
    @JoinColumn(name = "id_persona", unique = true)
    private Persona persona;

    @Column(name = "num_hours_week")
    private Integer numHoursWeek;

    @Column(name = "comments")
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private Professor professor;

    @Column(name = "branch")
    @Enumerated(EnumType.STRING)
    private Branch branch;

    @OneToMany(mappedBy = "student")
    private Set<StudentAsignaturas> asignaturas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(idStudent, student.idStudent) && Objects.equals(persona, student.persona) && Objects.equals(numHoursWeek, student.numHoursWeek) && Objects.equals(comments, student.comments) && Objects.equals(professor, student.professor) && branch == student.branch && Objects.equals(asignaturas, student.asignaturas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudent, persona, numHoursWeek, comments, professor, branch, asignaturas);
    }
}
