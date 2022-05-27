package com.example.projetfinal.service;

import com.example.projetfinal.domain.Employe;
import com.example.projetfinal.domain.Ticket;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TicketService {
    Ticket createTicket(Ticket ticket);
    Optional<Ticket> readOneTicket(Integer id);
    List<Ticket> readAllTicket();
    void deleteTicket(Integer id);
    //List<Ticket> findTicketsByEmploye_id(Integer employe_id);
    List<Ticket> findTicketsByEmployeContains(Integer posterID);
    Collection<Ticket> searchTickets(String search);
    Ticket getById(Integer id);

}
