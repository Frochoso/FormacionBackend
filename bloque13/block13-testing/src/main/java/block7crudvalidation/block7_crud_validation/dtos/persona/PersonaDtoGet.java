package block7crudvalidation.block7_crud_validation.dtos.persona;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDtoGet {

    Integer idPersona;

    String usuario;

    String password;

    String name;

    String surname;

    String companyEmail;

    String personalEmail;

    String city;

    boolean active;

    LocalDate createdDate;

    String imagenUrl;

    LocalDate terminationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaDtoGet that = (PersonaDtoGet) o;
        return active == that.active && Objects.equals(idPersona, that.idPersona) && Objects.equals(usuario, that.usuario) && Objects.equals(password, that.password) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(companyEmail, that.companyEmail) && Objects.equals(personalEmail, that.personalEmail) && Objects.equals(city, that.city) && Objects.equals(createdDate, that.createdDate) && Objects.equals(imagenUrl, that.imagenUrl) && Objects.equals(terminationDate, that.terminationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersona, usuario, password, name, surname, companyEmail, personalEmail, city, active, createdDate, imagenUrl, terminationDate);
    }
}
