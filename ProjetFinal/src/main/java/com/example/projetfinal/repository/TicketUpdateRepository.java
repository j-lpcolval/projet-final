package com.example.projetfinal.repository;

import com.example.projetfinal.domain.TickUpdatePK;
import com.example.projetfinal.domain.TicketUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketUpdateRepository extends JpaRepository<TicketUpdate, Integer> {
    //List<TicketUpdate> getUpdatesByEmployeId(Integer employe_id);
    //List<TicketUpdate> getUpdatesByTicketId(Integer ticket_id);
}
