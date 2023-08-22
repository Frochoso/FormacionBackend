package block7crudvalidation.block7_crud_validation.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "File")
@NamedQuery(name = "File.findByName", query = "SELECT f FROM File f WHERE f.nombreFichero= :name")
public class File {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombreFichero")
    private String nombreFichero;

    @Column(name = "fechaSubida")
    private LocalDate fechaSubida;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "path")
    private String path;
}
