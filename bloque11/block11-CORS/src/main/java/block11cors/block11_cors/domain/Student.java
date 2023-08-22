package block11cors.block11_cors.domain;

import block11cors.block11_cors.enumerations.Branch;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
