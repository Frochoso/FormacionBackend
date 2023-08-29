package block7crudvalidation.block7_crud_validation.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
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

    public Persona(Integer idPersona, String usuario, String password, String name, String surname,
                   String companyEmail, String personalEmail, String city, boolean active,
                   LocalDate createdDate, String imagenUrl, LocalDate terminationDate) {
        this.idPersona = idPersona;
        this.usuario = usuario;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.companyEmail = companyEmail;
        this.personalEmail = personalEmail;
        this.city = city;
        this.active = active;
        this.createdDate = createdDate;
        this.imagenUrl = imagenUrl;
        this.terminationDate = terminationDate;
    }

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return active == persona.active && Objects.equals(idPersona, persona.idPersona) && Objects.equals(usuario, persona.usuario) && Objects.equals(password, persona.password) && Objects.equals(name, persona.name) && Objects.equals(surname, persona.surname) && Objects.equals(companyEmail, persona.companyEmail) && Objects.equals(personalEmail, persona.personalEmail) && Objects.equals(city, persona.city) && Objects.equals(createdDate, persona.createdDate) && Objects.equals(imagenUrl, persona.imagenUrl) && Objects.equals(terminationDate, persona.terminationDate) && Objects.equals(student, persona.student) && Objects.equals(professor, persona.professor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersona, usuario, password, name, surname, companyEmail, personalEmail, city, active, createdDate, imagenUrl, terminationDate, student, professor);
    }

}
