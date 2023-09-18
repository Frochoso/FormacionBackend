package com.example.backend.service.interfaces;

import com.example.backend.dto.viaje.ViajeDtoGet;
import com.example.backend.dto.viaje.ViajeDtoPost;

public interface ViajeService {

    ViajeDtoGet addViaje(ViajeDtoPost viajeDtoPost);

    ViajeDtoGet removeViaje(int idViaje);

    ViajeDtoGet updateViaje(int idViaje, ViajeDtoPost viajeDtoPost);

    ViajeDtoGet getViaje(int idViaje);

    ViajeDtoGet addPassenger(int idViaje, int idCliente);

    ViajeDtoGet updateTripStatus(int idViaje, int newStatus);

    int tripStatus(int idViaje);

}
