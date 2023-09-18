package com.example.backend.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@NamedQuery(
        name = "Cliente.passengerCount",
        query = "SELECT COUNT(c) FROM Cliente c WHERE c.trip.idViaje = :idViaje",
        hints = @QueryHint(name = "org.hibernate.fetchSize", value = "40")
)
public class Cliente {

    @Id
    @GeneratedValue
    private int id;

    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private int tfno;

    @ManyToOne
    @JoinColumn(name = "idViaje")
    private Viaje trip;


}
