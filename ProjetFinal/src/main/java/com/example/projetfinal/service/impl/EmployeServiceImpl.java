package com.example.projetfinal.service.impl;

import com.example.projetfinal.domain.Employe;
import com.example.projetfinal.repository.EmployeRepository;
import com.example.projetfinal.service.EmployeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeServiceImpl implements EmployeService {

    private final EmployeRepository employeRepository;

    public EmployeServiceImpl(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public Employe createEmploye(Employe employe) {
        return this.employeRepository.save(employe);
    }

    @Override
    public Optional<Employe> readOneEmploye(Integer id) {
        return employeRepository.findById(id);
    }

    @Override
    public List<Employe> readAllEmploye() {
        return this.employeRepository.findAll();
    }

    @Override
    public void deleteEmploye(Integer id) {
            this.employeRepository.deleteById(id);
    }

    @Override
    public Employe readByName(String name) {
        return this.employeRepository.findByUsername(name);
    }

    @Override
    public Collection<Employe> searchEmploye(String search) {
        return readAllEmploye()
                .stream()
                .filter(e -> e.getUsername()
                        .toLowerCase()
                        .contains(search.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Object> getEmployeDetailByID(Integer employe_id) {
        return employeRepository.getEmployeDetailByID(employe_id);
    }
}
