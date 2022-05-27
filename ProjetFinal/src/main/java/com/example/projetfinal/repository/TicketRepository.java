package com.example.projetfinal.repository;

import com.example.projetfinal.domain.Employe;
import com.example.projetfinal.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TicketRepository extends JpaRepository<Ticket,Integer> {

    //List<Ticket> findTicketsByEmployeEmploye(Integer employe_id);
    List<Ticket> findTicketsByEmployeContains(Integer posterID);
    //List<Ticket> findTicketsByEmployeid(Integer employe_id);
}
