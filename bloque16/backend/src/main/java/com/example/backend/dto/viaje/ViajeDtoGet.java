package com.example.backend.dto.viaje;

import com.example.backend.dto.cliente.ClienteDtoGet;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ViajeDtoGet {

    private int idViaje;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private List<ClienteDtoGet> passenger;
    private int status;

}