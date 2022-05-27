package com.example.projetfinal.security;

import com.example.projetfinal.domain.Employe;
import com.example.projetfinal.repository.EmployeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {


    private final EmployeRepository employeRepository;

    public UserPrincipalDetailsService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Employe employe = this.employeRepository.findByUsername(username);//.orElseThrow(() -> new UsernameNotFoundException("Employe login not found"));

        return new UserPrincipal(employe);
    }
}
