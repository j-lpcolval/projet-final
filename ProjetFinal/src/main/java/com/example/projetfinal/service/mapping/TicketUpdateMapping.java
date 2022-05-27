package com.example.projetfinal.service.mapping;

import com.example.projetfinal.domain.Employe;
import com.example.projetfinal.domain.Ticket;
import com.example.projetfinal.domain.TicketUpdate;
import com.example.projetfinal.service.dto.TicketUpdateDTO;

public class TicketUpdateMapping {

    private TicketUpdateMapping()
    {

    }

    public static TicketUpdate populateTicketUpdate(TicketUpdateDTO ticketUpdateDTO , Ticket ticket , Employe employe)
    {

        TicketUpdate tu = new TicketUpdate();
        tu.setTupdateId(ticketUpdateDTO.getTupdateId());
        tu.setTicket(ticket);
        tu.setEmploye(employe);
        tu.setDate(ticketUpdateDTO.getDate());
        tu.setMessage(ticketUpdateDTO.getMessage());

        return tu;
    }
}
