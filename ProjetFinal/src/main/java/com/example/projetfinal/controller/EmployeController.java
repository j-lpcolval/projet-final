package com.example.projetfinal.controller;

import com.example.projetfinal.ProjetFinalApplication;
import com.example.projetfinal.domain.Employe;
import com.example.projetfinal.domain.Ticket;
import com.example.projetfinal.service.EmployeService;
import com.example.projetfinal.service.dto.EmployeDTO;
import com.example.projetfinal.service.mapping.EmployeMapping;
import com.example.projetfinal.viewmodel.SearchViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("employes")
public class EmployeController {

    private final EmployeService employeService;
    private final Logger log = LoggerFactory.getLogger(EmployeController.class);

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping
    public String showEmployePage(Model model, @ModelAttribute SearchViewModel search)
    {
        model.addAttribute("search" , search);
        model.addAttribute("employes",employeService.readAllEmploye());
        return "pages/employe/employe_list";
    }

    @GetMapping("addemploye")
    public String showEmployeAddPage(Model model)
    {
        model.addAttribute("employe" , new EmployeDTO());
        return "pages/employe/employe_add";
    }

    @PostMapping("addemploye")
    public RedirectView saveEmploye(EmployeDTO employeDTO)
    {
        final Employe employe = EmployeMapping.populateEmploye(employeDTO);
        employeService.createEmploye(employe);
        return new RedirectView("/employes");
    }

    @PostMapping("editemploye")
    public RedirectView editEmploye(Employe employe)
    {
        log.info("*******" + employe);
        employe.setMdp(new BCryptPasswordEncoder().encode(employe.getPassword()));
        log.info(employe.getRole());
        employeService.createEmploye(employe);

        log.info("*******" + employe);
        log.info(employe.getRole());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usrName = authentication.getName();
        Employe emp = employeService.readByName(usrName);

        if(emp.getRole().equals("ROLE_ADMIN"))
        {
            return new RedirectView("/employes");
        }else if(emp.getRole().equals("ROLE_USER"))
        {
            return new RedirectView("/user");
        }
        return new RedirectView("/employes");
    }


    @GetMapping("/edit/{id}")
    public String showEditEmploye(@PathVariable Integer id , Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usrName = authentication.getName();
        Employe emp = employeService.readByName(usrName);
        Optional<Employe> employe = employeService.readOneEmploye(id);
        if(employe.isPresent())
        {
            model.addAttribute("employe",employe.get());
            model.addAttribute("current", emp);
            return "pages/employe/employe_edit";
        }else
        {
            return showEmployeNotFound(model , id);
        }
    }

    @GetMapping("/{id}")
    public String showEmployeDetail(@PathVariable Integer id , Model model)
    {
        Optional<Employe> employe = employeService.readOneEmploye(id);
        if(employe.isPresent())
        {
            model.addAttribute("employe", employe.get());
            return "pages/employe/employe_detail";
        }else
        {
            return showEmployeNotFound(model ,id);
        }
    }

    @GetMapping("delete/{id}")
    public String deleteEmploye(@PathVariable Integer id , Model model, @ModelAttribute SearchViewModel search)
    {
        employeService.deleteEmploye(id);
        model.addAttribute("search" , search);
        model.addAttribute("employes",employeService.readAllEmploye());
        return "pages/employe/employe_list";
    }

    @GetMapping("/executeSearch")
    public String searchCustomer(@ModelAttribute SearchViewModel search, Model model) {
        Collection<Employe> employes = employeService.searchEmploye(search.getQuery()== null ? "test" : search.getQuery());
        model.addAttribute("search", search);
        model.addAttribute("employes",
                employes);
        return "pages/employe/employe_list";
    }

    private String showEmployeNotFound(Model model, Integer id) {
        model.addAttribute("id" , id);
        return "pages/employe/employe_not_found";
    }
}
