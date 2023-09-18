package com.example.backendfrontend.service;

import com.example.backendfrontend.dto.ticket.TicketDtoGet;

public interface TicketService {

    TicketDtoGet generateticket(int idCliente, int idViaje);

}
