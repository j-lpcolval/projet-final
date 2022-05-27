package com.example.projetfinal.security;

import com.example.projetfinal.domain.Employe;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private  Employe employe;

    public UserPrincipal(Employe employe) {
        this.employe = employe;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority(employe.getRole());
        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return this.employe.getMdp();
    }

    @Override
    public String getUsername() {
        return this.employe.getUsername();
    }


    public Integer getId() {return this.employe.getEmploye_id();}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
