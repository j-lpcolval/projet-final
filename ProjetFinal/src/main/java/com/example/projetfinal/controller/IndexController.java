package com.example.projetfinal.controller;

import com.example.projetfinal.domain.Employe;
import com.example.projetfinal.repository.EmployeRepository;
import com.example.projetfinal.service.EmployeService;
import com.example.projetfinal.service.TicketService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping()
public class IndexController {

    EmployeRepository employeRepository;
    EmployeService employeService;

    public IndexController(EmployeService employeService) {

        this.employeService = employeService;
    }

    @GetMapping
    public String showIndex(Model model)
    {
        String welcome = "Projet final Hello";

        model.addAttribute("welcome",welcome);

        return "pages/index";
    }


    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usrName = authentication.getName();

        Employe emp = employeService.readByName(usrName);
        model.addAttribute("emp" , emp);
        return "pages/admin";
    }

    @GetMapping("/user")
    public String showUserPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usrName = authentication.getName();

        Employe emp = employeService.readByName(usrName);
        model.addAttribute("emp" , emp);
        return "pages/user";
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usrName = authentication.getName();

        Employe emp = employeService.readByName(usrName);
        model.addAttribute("emp" , emp);
        return "pages/accessdenied";
    }

}
