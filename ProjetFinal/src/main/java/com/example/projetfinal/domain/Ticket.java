package com.example.projetfinal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")
@NamedQueries({
        @NamedQuery(name = "Ticket.getAllByEmployeID", query =
                "SELECT t.ticketId , t.ticketDescription , t.ticketType from Ticket t join Employe e where e.employe_id = t.employe.employe_id and e.employe_id = :ids")
})
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticketId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poster_Id")
    private Employe employe;

    @Column(name = "ticket_description")
    private String ticketDescription;

    @Column(name = "ticket_type")
    private String ticketType;

    @Column(name = "dateposted")
    private java.sql.Date dateposted;

    @Column(name = "is_resolved")
    private Byte isResolved;

    public Integer getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Employe getEmploye() {
        return this.employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public String getTicketDescription() {
        return this.ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public String getTicketType() {
        return this.ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public java.sql.Date getDateposted() {
        return this.dateposted;
    }

    public void setDateposted(java.sql.Date dateposted) {
        this.dateposted = dateposted;
    }

    public Byte getIsResolved() {
        return this.isResolved;
    }

    public void setIsResolved(Byte isResolved) {
        this.isResolved = isResolved;
    }
}
