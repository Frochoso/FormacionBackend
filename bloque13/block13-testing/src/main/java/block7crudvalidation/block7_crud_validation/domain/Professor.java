package block7crudvalidation.block7_crud_validation.domain;


import block7crudvalidation.block7_crud_validation.enumerations.Branch;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "professor")
@NamedQuery(name = "Professor.findByName", query = " SELECT p FROM Professor p WHERE p.persona.name = :name")
public class Professor {

    @Id
    @GeneratedValue
    @Column(name = "id_professor")
    private Integer idProfesor;

    @OneToOne
    @JoinColumn(name = "id_persona", unique = true)
    private Persona persona;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "professor")
    private Set<Student> students;

    @Column(name = "comments")
    private String comments;

    @Column(name = "branch")
    @Enumerated(EnumType.STRING)
    private Branch branch;

    public Professor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Professor(Integer idProfesor, Persona persona, String comments, Branch branch) {
        this.idProfesor = idProfesor;
        this.persona = persona;
        this.comments = comments;
        this.branch = branch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equals(idProfesor, professor.idProfesor) && Objects.equals(persona, professor.persona) && Objects.equals(students, professor.students) && Objects.equals(comments, professor.comments) && branch == professor.branch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProfesor, persona, students, comments, branch);
    }
}
