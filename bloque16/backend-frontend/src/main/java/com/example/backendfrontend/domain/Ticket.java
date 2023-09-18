package com.example.backendfrontend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue
    int id;

    int passengerId;

    String passengerName;

    String passengerLastname;

    String passengenerEmail;

    String tripOrigin;

    String tripDestination;

    LocalDate departureDate;

    LocalDate arrivalDate;

}
