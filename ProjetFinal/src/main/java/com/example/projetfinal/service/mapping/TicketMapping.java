package com.example.projetfinal.service.mapping;

import com.example.projetfinal.domain.Employe;
import com.example.projetfinal.domain.Ticket;
import com.example.projetfinal.service.EmployeService;
import com.example.projetfinal.service.dto.TicketDTO;

public class TicketMapping {

    private TicketMapping()
    {

    }

    public static Ticket populateTicket(TicketDTO ticketDTO , Employe employe)
    {
        Ticket ticket = new Ticket();

        ticket.setTicketId(ticketDTO.getTicketId());
        ticket.setEmploye(employe);
        ticket.setTicketDescription(ticketDTO.getTicketDescription());
        ticket.setTicketType(ticketDTO.getTicketType());
        ticket.setDateposted(ticketDTO.getDateposted());
        ticket.setIsResolved(ticketDTO.getIsResolved());
        return ticket;
    }

}
