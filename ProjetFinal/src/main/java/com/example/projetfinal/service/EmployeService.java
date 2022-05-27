package com.example.projetfinal.service;

import com.example.projetfinal.domain.Employe;
import com.example.projetfinal.domain.Ticket;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EmployeService {
    Employe createEmploye(Employe employe);
    Optional<Employe> readOneEmploye(Integer id);
    List<Employe> readAllEmploye();
    void deleteEmploye(Integer id);
    Employe readByName(String name);
    Collection<Employe> searchEmploye(String search);
    Optional<Object> getEmployeDetailByID(Integer id);
}
