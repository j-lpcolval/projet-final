package com.example.projetfinal.domain;

import javax.persistence.*;

@Entity
@Table(name = "ticket_update")
public class TicketUpdate {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_Id")
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employe_id")
    private Employe employe;

    @Column(name = "date")
    private java.sql.Date date;

    @Column(name = "message")
    private String message;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tupdate_id")
    private Integer tupdateId;

    public Ticket getTicket() {
        return this.ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Employe getEmploye() {
        return this.employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public java.sql.Date getDate() {
        return this.date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTupdateId() {
        return this.tupdateId;
    }

    public void setTupdateId(Integer tupdateId) {
        this.tupdateId = tupdateId;
    }
}
