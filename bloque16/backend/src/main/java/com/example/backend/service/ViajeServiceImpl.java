package com.example.backend.service;

import com.example.backend.domain.Cliente;
import com.example.backend.domain.Viaje;
import com.example.backend.dto.viaje.ViajeDtoGet;
import com.example.backend.dto.viaje.ViajeDtoPost;
import com.example.backend.mappers.ClienteMapper;
import com.example.backend.mappers.ViajeMapper;
import com.example.backend.repository.ViajeRepository;
import com.example.backend.service.interfaces.ClienteService;
import com.example.backend.service.interfaces.ViajeService;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViajeServiceImpl implements ViajeService {

    @Autowired
    ViajeRepository viajeRepository;

    @Autowired
    ClienteService clienteService;

    ViajeMapper viajeMapper = Mappers.getMapper(ViajeMapper.class);

    ClienteMapper clienteMapper = Mappers.getMapper(ClienteMapper.class);

    @Override
    public ViajeDtoGet addViaje(ViajeDtoPost viajeDtoPost) {

        return viajeMapper.viajeToViajeDtoGet(viajeRepository.save(viajeMapper.viajeDtoPostToViaje(viajeDtoPost)));
    }

    @Override
    public ViajeDtoGet addPassenger(int idViaje, int idCliente) {

        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow(() -> new EntityNotFoundException());
        Cliente cliente = clienteMapper.clienteDtoGetToCliente(clienteService.findCliente(idCliente));

        viaje.getPassenger().add(cliente);

        return viajeMapper.viajeToViajeDtoGet(viajeRepository.save(viaje));
    }

    @Override
    public ViajeDtoGet updateTripStatus(int idViaje, int newStatus) {
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow(() ->
                new EntityNotFoundException("Viaje con id: " + idViaje + " no encontrado."));

        viaje.setStatus(newStatus);
        return viajeMapper.viajeToViajeDtoGet(viajeRepository.save(viaje));
    }

    @Override
    public int tripStatus(int idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow(() ->
                new EntityNotFoundException("Viaje con id: " + idViaje + " no encontrado."));

        return viaje.getStatus();
    }

    @Override
    public ViajeDtoGet removeViaje(int idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow(() ->
                new EntityNotFoundException("Viaje con id: " + idViaje + " no encontrado."));
        viajeRepository.delete(viaje);
        return viajeMapper.viajeToViajeDtoGet(viaje);
    }

    @Override
    public ViajeDtoGet updateViaje(int idViaje, ViajeDtoPost viajeDtoPost) {
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow(() ->
                new EntityNotFoundException("Viaje con id: " + idViaje + " no encontrado."));
        viaje.setArrivalDate(viajeDtoPost.getArrivalDate());
        viaje.setDestination(viajeDtoPost.getDestination());
        viaje.setOrigin(viajeDtoPost.getOrigin());
        viaje.setStatus(viajeDtoPost.getStatus());
        viaje.setDepartureDate(viajeDtoPost.getDepartureDate());

        return viajeMapper.viajeToViajeDtoGet(viaje);
    }

    @Override
    public ViajeDtoGet getViaje(int idViaje) {
        return viajeMapper.viajeToViajeDtoGet(viajeRepository.findById(idViaje).orElseThrow(() ->
                new EntityNotFoundException("Viaje con id: " + idViaje + " no encontrado.")));
    }
}
