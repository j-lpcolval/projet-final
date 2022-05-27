package com.example.projetfinal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "employe")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Employe.getEmployeDetailByID", query =
        "select e.employe_id , e.username , e.password, e.email from Employe e where e.employe_id = :id")
})
public class Employe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employe_id")
    private Integer employe_id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "ticketId")
    private Collection<Ticket> listeTickets;

    @Column(name = "Mdp")
    private String mdp;

    @Column(name = "role")
    private String role;

    public String getRole() {return this.role;}

    public void setRole(String role) {this.role = role;}

    public Integer getEmploye_id() {
        return this.employe_id;
    }

    public void setEmploye_id(Integer employeId) {
        this.employe_id = employeId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return this.mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
