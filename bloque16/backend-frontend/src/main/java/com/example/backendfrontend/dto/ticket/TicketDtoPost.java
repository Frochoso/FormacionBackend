package com.example.backendfrontend.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TicketDtoPost {

    int passengerId;

    String passengerName;

    String passengerLastname;

    String passengenerEmail;

    String tripOrigin;

    String tripDestination;

    LocalDate departureDate;

    LocalDate arrivalDate;

}
