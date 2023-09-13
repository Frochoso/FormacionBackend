package block7crudvalidation.block7_crud_validation.domain;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "Asignatura.findAsignaturas", query = "SELECT a FROM Asignatura a WHERE a.idAsignatura = :idAsignatura")
public class Asignatura {

    @Id
    @GeneratedValue
    private Integer idAsignatura;

    @OneToMany(mappedBy = "asignatura")
    private Set<StudentAsignaturas> students;

    @Column(name = "nombre_asignatura")
    private String nombreAsignatura;

    @Column(name = "comments")
    private String comments;

    @Column(name = "initial_date")
    private LocalDate initialDate;

    @Column(name = "finish_date")
    private LocalDate finishDate;

    public Asignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Asignatura(Integer idAsignatura, String nombreAsignatura, String comments, LocalDate initialDate, LocalDate finishDate) {
        this.idAsignatura = idAsignatura;
        this.nombreAsignatura = nombreAsignatura;
        this.comments = comments;
        this.initialDate = initialDate;
        this.finishDate = finishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asignatura that = (Asignatura) o;
        return Objects.equals(idAsignatura, that.idAsignatura) && Objects.equals(students, that.students)
                && Objects.equals(nombreAsignatura, that.nombreAsignatura) && Objects.equals(comments, that.comments)
                && Objects.equals(initialDate, that.initialDate) && Objects.equals(finishDate, that.finishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAsignatura, students, nombreAsignatura, comments, initialDate, finishDate);
    }
}
