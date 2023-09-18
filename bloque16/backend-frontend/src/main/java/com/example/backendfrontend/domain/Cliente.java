package com.example.backendfrontend.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
