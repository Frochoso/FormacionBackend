package com.example.backendfrontend.mappers;

import com.example.backendfrontend.domain.Ticket;
import com.example.backendfrontend.dto.ticket.TicketDtoGet;
import com.example.backendfrontend.dto.ticket.TicketDtoPost;
import org.mapstruct.Mapper;

@Mapper
public interface TicketMapper {

    Ticket ticketDtoPostToTicket(TicketDtoPost ticketDtoPost);

    TicketDtoGet ticketToTicketDtoGet(Ticket ticket);
}
