package block11cors.block11_cors.domain;


import block11cors.block11_cors.enumerations.Branch;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "professor")
@NamedQuery(name = "Professor.findByName",query = " SELECT p FROM Professor p WHERE p.persona.name = :name")
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


}
