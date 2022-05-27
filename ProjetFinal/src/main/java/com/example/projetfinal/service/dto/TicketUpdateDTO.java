package com.example.projetfinal.service.dto;

import com.example.projetfinal.domain.Employe;
import com.example.projetfinal.domain.Ticket;
import lombok.Data;

@Data
public class TicketUpdateDTO {
    private Integer tupdate_id;
    private Ticket ticket;
    private Employe employe;
    private java.sql.Date date;
    private String message;

    public Ticket getTicket() {
        return this.ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    public Integer getTupdateId(){return this.tupdate_id;}

    public void setTupdateId(Integer tupdate_id){this.tupdate_id = tupdate_id;}

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
}
