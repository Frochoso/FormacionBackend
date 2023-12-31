package block11cors.block11_cors.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    public Asignatura(Integer idAsignatura, String nombreAsignatura, String comments, LocalDate initialDate, LocalDate finishDate) {
        this.idAsignatura = idAsignatura;
        this.nombreAsignatura = nombreAsignatura;
        this.comments = comments;
        this.initialDate = initialDate;
        this.finishDate = finishDate;
    }
}
