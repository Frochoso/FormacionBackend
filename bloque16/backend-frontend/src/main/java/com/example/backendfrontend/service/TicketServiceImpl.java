package com.example.backendfrontend.service;

import com.example.backendfrontend.domain.Cliente;
import com.example.backendfrontend.domain.Viaje;
import com.example.backendfrontend.dto.ticket.TicketDtoGet;
import com.example.backendfrontend.dto.ticket.TicketDtoPost;
import com.example.backendfrontend.feign.Feign;
import com.example.backendfrontend.mappers.ClienteMapper;
import com.example.backendfrontend.mappers.TicketMapper;
import com.example.backendfrontend.mappers.ViajeMapper;
import com.example.backendfrontend.repository.TicketRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    Feign feign;

    ClienteMapper clienteMapper = Mappers.getMapper(ClienteMapper.class);
    ViajeMapper viajeMapper = Mappers.getMapper(ViajeMapper.class);

    TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);

    @Override
    public TicketDtoGet generateticket(int idCliente, int idViaje) {

        Cliente cliente = clienteMapper.clienteDtoGetToCliente(feign.findCliente(idCliente));
        Viaje viaje = viajeMapper.viajeDtoGetToViaje(feign.findViaje(idViaje));

        TicketDtoPost ticketDtoPost = new TicketDtoPost(cliente.getId(), cliente.getNombre(), cliente.getApellido(),
                cliente.getEmail(), viaje.getOrigin(), viaje.getDestination(),
                viaje.getDepartureDate(), viaje.getArrivalDate());

        return ticketMapper.ticketToTicketDtoGet(ticketRepository.save(
                ticketMapper.ticketDtoPostToTicket(ticketDtoPost)));
    }
}
