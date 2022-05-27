package com.example.projetfinal.service;

import com.example.projetfinal.domain.TicketUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketUpdateService{
    TicketUpdate createTicketUpdate(TicketUpdate ticketUpdate);
    Optional<TicketUpdate> readOneTicketUpdate(Integer id);
    List<TicketUpdate> readAllTicketUpdate();
    void deleteTicketUpdate(Integer id);
   // List<TicketUpdate> getUpdatesByTicketId(Integer id);
    //List<TicketUpdate> getUpdatesByEmployeId(Integer id);
}
