package com.example.backendfrontend.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Viaje {

    @Id
    @GeneratedValue
    private int idViaje;

    private String origin;

    private String destination;

    private LocalDate departureDate;

    private LocalDate arrivalDate;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.REMOVE)
    private List<Cliente> passenger;

    private int status;

}
