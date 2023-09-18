package com.example.backendfrontend.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class TicketDtoGet {


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
