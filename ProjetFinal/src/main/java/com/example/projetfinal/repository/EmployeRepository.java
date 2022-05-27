package com.example.projetfinal.repository;

import com.example.projetfinal.domain.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeRepository extends JpaRepository<Employe,Integer> {
    Employe findByUsername(String username);
    Optional<Object> getEmployeDetailByID(Integer id);
}
