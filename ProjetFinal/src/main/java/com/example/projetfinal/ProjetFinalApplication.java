package com.example.projetfinal;

import com.example.projetfinal.domain.Ticket;
import com.example.projetfinal.service.EmployeService;
import com.example.projetfinal.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ProjetFinalApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProjetFinalApplication.class, args);
    }

    private final Logger log = LoggerFactory.getLogger(ProjetFinalApplication.class);

    @Autowired
    private EmployeService employeService;

    @Autowired
    private TicketService ticketService;

    @Override
    public void run(String... args) throws Exception {
        log.info("test");

        findEmployeDetailById(2);
        //findAllByEmploye(2);
    }

    private void findAllByEmploye(Integer employe_id) {
        log.info("Request get tickets by empID :" , employe_id);
        List<Ticket> tickets = ticketService.findTicketsByEmployeContains(employe_id);
        tickets.forEach( t -> log.info("Ticket",t));
        //List<Ticket> tickets = ticketService.findTicketsByEmploye_Employe_idContains(employe_id);
    }

    void findEmployeDetailById(Integer i) {
        log.info("Request get employe detail by id :" , i);
        Optional<Object> employe = employeService.getEmployeDetailByID(i);
        if(employe.isPresent())
        {
            log.info("Employe with id : {} found : {}" , i,employe.get());
        }else
        {
            log.info("Employe not found");
        }
    }


}
