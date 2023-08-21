package block7crudvalidation.block7_crud_validation.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Persona")
@NamedQuery(name = "Persona.findByUsuario", query = "SELECT p FROM Persona p WHERE p.usuario = :usuario")
@NamedQuery(name = "Persona.findStudentAsociado", query = "SELECT p.student FROM Persona p WHERE p.student.persona.idPersona = :idPersona")
public class Persona {

    @Id
    @GeneratedValue
    @Column(name = "id_persona")
    private Integer idPersona;
    @Column(name = "usuario")
    private String usuario;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "company_email")
    private String companyEmail;

    @Column(name = "personal_email")
    private String personalEmail;

    @Column(name = "city")
    private String city;

    @Column(name = "active")
    private boolean active;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "termination_date")
    private LocalDate terminationDate;

    @OneToOne(mappedBy = "persona")
    private Student student;

    @OneToOne(mappedBy = "persona")
    private Professor professor;
}
