package com.example.projetfinal.controller;


import com.example.projetfinal.domain.Employe;
import com.example.projetfinal.domain.Ticket;
import com.example.projetfinal.domain.TicketUpdate;
import com.example.projetfinal.service.EmployeService;
import com.example.projetfinal.service.TicketService;
import com.example.projetfinal.service.TicketUpdateService;
import com.example.projetfinal.service.dto.TicketDTO;
import com.example.projetfinal.service.dto.TicketUpdateDTO;
import com.example.projetfinal.service.mapping.TicketMapping;
import com.example.projetfinal.service.mapping.TicketUpdateMapping;
import com.example.projetfinal.viewmodel.SearchViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("tickets")
public class TicketController {
    private final TicketService ticketService;

    private final EmployeService employeService;
    private final TicketUpdateService ticketUpdateService;


    private final Logger log = LoggerFactory.getLogger(TicketController.class);

    public TicketController(TicketService ticketService, EmployeService employeService, TicketUpdateService ticketUpdateService) {
        this.ticketService = ticketService;
        this.employeService = employeService;

        this.ticketUpdateService = ticketUpdateService;
    }


    @GetMapping
    public String showTicketPage(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usrName = authentication.getName();
        Employe emp = employeService.readByName(usrName);

        List<Ticket> tickets = ticketService.readAllTicket().stream().filter(t -> t.getIsResolved() == 0).collect(Collectors.toList());
        model.addAttribute("tickets" , tickets);
        model.addAttribute("employe" ,emp);

        return "pages/ticket/ticket_list";

    }



    @GetMapping("postticket")
    public String showEmployeAddPage(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usrName = authentication.getName();
        Employe emp = employeService.readByName(usrName);
        model.addAttribute("current" , emp);
        model.addAttribute("ticket" , new TicketDTO());

       // model.addAttribute("emp" , emp);

        return "pages/ticket/ticket_add";
    }

    @PostMapping("postticket")
    public RedirectView saveTicket(TicketDTO ticketDTO)
    {
        long millis=System.currentTimeMillis();
        java.sql.Date current = new java.sql.Date(millis);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usrName = authentication.getName();
        Employe emp = employeService.readByName(usrName);
        final Ticket ticket = TicketMapping.populateTicket(ticketDTO , emp);
        ticket.setDateposted(current);
        ticket.setEmploye(emp);
        ticket.setIsResolved((byte) 0);
        ticketService.createTicket(ticket);

        if(emp.getRole().equals("ROLE_USER"))
        {
            log.info("user found");
            return new RedirectView("/tickets/mytickets");

        }else if(emp.getRole().equals("ROLE_ADMIN"))
        {
            log.info("admin found");
        return new RedirectView("/tickets");
        }
        return new RedirectView("/index");
    }

    @GetMapping("/{id}")
    public String showTicketDetail(@PathVariable Integer id , Model model)
    {
        Optional<Ticket> ticket = ticketService.readOneTicket(id);
        List<TicketUpdate> ticketUpdates = ticketUpdateService.readAllTicketUpdate().stream().filter(tu -> tu.getTicket().getTicketId() == id).collect(Collectors.toList());
        ticketUpdates.sort(Comparator.comparing(TicketUpdate::getDate));
        TicketUpdate ticketUpdate = new TicketUpdate();


        log.info(ticketUpdates.toString());
        if(ticket.isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String usrName = authentication.getName();
            Employe emp = employeService.readByName(usrName);
            model.addAttribute("current" , emp);

            model.addAttribute("ticket", ticket.get());
            model.addAttribute("comments" , ticketUpdates);
            model.addAttribute("ticketupdate",ticketUpdate);
            return "pages/ticket/ticket_detail";
        }else
        {
            return showTicketNotFound(model ,id);
        }
    }

    @GetMapping("commentpost/{id}")
    public RedirectView postComment(@PathVariable Integer id, Model model)
    {
        //Ticket ticket = ticketService.getById(id);
        model.addAttribute("ticketupdate" , new TicketUpdateDTO());
        //model.addAttribute("ticket" , ticket);
        // model.addAttribute("emp" , emp);

        return new RedirectView("/tickets/"+id);
    }



    @PostMapping("commentpost/{id}")
    public RedirectView postComment(@PathVariable Integer id,TicketUpdateDTO ticketUpdateDTO)
    {

        Ticket ticket = ticketService.getById(id);
        long millis=System.currentTimeMillis();
        java.sql.Date current = new java.sql.Date(millis);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usrName = authentication.getName();
        Employe emp = employeService.readByName(usrName);
        final TicketUpdate ticketUpdate = TicketUpdateMapping.populateTicketUpdate(ticketUpdateDTO ,ticket,emp);
        ticketUpdate.setDate(current);
        ticketUpdate.setEmploye(emp);
        ticketUpdate.setTicket(ticket);
        log.info(ticketUpdate.toString());
        ticketUpdateService.createTicketUpdate(ticketUpdate);

        return new RedirectView("/tickets/"+id);
    }



    @GetMapping("delete/{id}")
    public RedirectView deleteTicket(@PathVariable Integer id , Model model)
    {

        for (TicketUpdate tu : ticketUpdateService.readAllTicketUpdate())
        {
            log.info(tu.getTupdateId().toString());
            if(tu.getTicket().getTicketId() == id)
            {
                ticketUpdateService.deleteTicketUpdate(tu.getTupdateId());
            }
        }
        ticketService.deleteTicket(id);
        List<Ticket> tickets = ticketService.readAllTicket().stream().filter(t -> t.getIsResolved() == 0).collect(Collectors.toList());
        model.addAttribute("tickets",tickets);
        return new RedirectView("/tickets");
    }

    private String showTicketNotFound(Model model, Integer id) {
        model.addAttribute("id" , id);
        return "pages/ticket/ticket_not_found";
    }

    @GetMapping("mytickets")
    public String showMyTicketsPage(Model model)
    {   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usrName = authentication.getName();
        Employe emp = employeService.readByName(usrName);
        List<Ticket> tickets = ticketService.readAllTicket().stream().filter(t -> t.getEmploye().getEmploye_id() == emp.getEmploye_id()).collect(Collectors.toList());
        model.addAttribute("tickets" , tickets);
        return "pages/ticket/user_ticket_list";

    }

    @GetMapping("archives")
    public String showTicketArchive(Model model ,@ModelAttribute SearchViewModel search)
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usrName = authentication.getName();
        Employe emp = employeService.readByName(usrName);
        model.addAttribute("current" , emp);
        List<Ticket> tickets = ticketService.readAllTicket().stream().filter(t -> t.getIsResolved() == 1).collect(Collectors.toList());
        model.addAttribute("tickets" , tickets);
        model.addAttribute("search" , search);
        return "pages/ticket/ticket_archives";

    }

    @GetMapping("moveToArchive/{id}")
    public RedirectView moveTicket(@PathVariable Integer id , Model model)
    {
        Ticket ticket = ticketService.getById(id);
        ticket.setIsResolved((byte) 1);
        ticketService.createTicket(ticket);
        //List<Ticket> tickets = ticketService.readAllTicket().stream().filter(t -> t.getIsResolved() == 0).collect(Collectors.toList());
        //model.addAttribute("tickets",tickets);
        return new RedirectView("/tickets/archives");
    }

    @GetMapping("/executeSearch")
    public String searchCustomer(@ModelAttribute SearchViewModel search, Model model) {
        Collection<Ticket> tickets = ticketService.searchTickets(search.getQuery()== null ? "test" : search.getQuery()).stream().filter(t -> t.getIsResolved() == 1).collect(Collectors.toList());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usrName = authentication.getName();
        Employe emp = employeService.readByName(usrName);
        model.addAttribute("current",emp);
        model.addAttribute("search", search);
        model.addAttribute("tickets",
                tickets);
        return "pages/ticket/ticket_archives";
    }

}
