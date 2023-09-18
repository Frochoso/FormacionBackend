package com.example.backendfrontend.controller;

import com.example.backendfrontend.dto.ticket.TicketDtoGet;
import com.example.backendfrontend.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/generateTicket/{idCliente}/{idViaje}")
    public TicketDtoGet generateTicket(@PathVariable int idCliente, @PathVariable int idViaje) {
        return ticketService.generateticket(idCliente, idViaje);
    }

}
