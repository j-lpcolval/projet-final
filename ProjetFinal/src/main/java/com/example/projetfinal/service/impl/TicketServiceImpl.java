package com.example.projetfinal.service.impl;

import com.example.projetfinal.domain.Employe;
import com.example.projetfinal.domain.Ticket;
import com.example.projetfinal.repository.TicketRepository;
import com.example.projetfinal.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> readOneTicket(Integer id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> readAllTicket() {
        return ticketRepository.findAll();
    }

    @Override
    public void deleteTicket(Integer id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public List<Ticket> findTicketsByEmployeContains(Integer posterID) {
        return ticketRepository.findTicketsByEmployeContains(posterID);
    }

    @Override
    public Collection<Ticket> searchTickets(String search) {
        return readAllTicket()
                .stream()
                .filter(
                        t -> t.getTicketDescription()
                                .toLowerCase()
                                .contains(search.toLowerCase())
                ).collect(Collectors.toList());
    }

    @Override
    public Ticket getById(Integer id) {
        return ticketRepository.getById(id);
    }
/*
    @Override
    public List<Ticket> findTicketsByEmploye_Employe_idContains(Integer employe_id) {
        return ticketRepository.findTicketsByEmploye_Employe_idContains(employe_id);
    }
*/

}
