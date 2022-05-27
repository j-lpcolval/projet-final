package com.example.projetfinal.service.mapping;

import com.example.projetfinal.domain.Employe;
import com.example.projetfinal.service.dto.EmployeDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EmployeMapping {

    private EmployeMapping()
    {

    }


    public static Employe populateEmploye(EmployeDTO employeDTO)
    {
        Employe employe = new Employe();

        employe.setUsername(employeDTO.getUsername());
        employe.setPassword(employeDTO.getPassword());
        employe.setEmail(employeDTO.getEmail());
        employe.setMdp(new BCryptPasswordEncoder().encode(employeDTO.getPassword()));
        employe.setRole(employeDTO.getRole());

        return employe;
    }


}
