package com.example.projetfinal.service.impl;

import com.example.projetfinal.domain.TicketUpdate;
import com.example.projetfinal.repository.TicketUpdateRepository;
import com.example.projetfinal.service.TicketUpdateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketUpdateServiceImpl implements TicketUpdateService {

    private final TicketUpdateRepository ticketUpdateRepository;

    public TicketUpdateServiceImpl(TicketUpdateRepository ticketUpdateRepository) {
        this.ticketUpdateRepository = ticketUpdateRepository;
    }

    @Override
    public TicketUpdate createTicketUpdate(TicketUpdate ticketUpdate) {
        return ticketUpdateRepository.save(ticketUpdate);
    }

    @Override
    public Optional<TicketUpdate> readOneTicketUpdate(Integer id) {
        return ticketUpdateRepository.findById(id);
    }

    @Override
    public List<TicketUpdate> readAllTicketUpdate() {
        return ticketUpdateRepository.findAll();
    }

    @Override
    public void deleteTicketUpdate(Integer id) {
        ticketUpdateRepository.deleteById(id);
    }

    /*
    @Override
    public List<TicketUpdate> getUpdatesByTicketId(Integer id) {
        return ticketUpdateRepository.getUpdatesByTicketId(id);
    }

    @Override
    public List<TicketUpdate> getUpdatesByEmployeId(Integer id) {
        return ticketUpdateRepository.getUpdatesByEmployeId(id);
    }

     */
}
