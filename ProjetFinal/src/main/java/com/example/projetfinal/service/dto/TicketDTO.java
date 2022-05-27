package com.example.projetfinal.service.dto;

import com.example.projetfinal.domain.Employe;
import lombok.Data;

@Data
public class TicketDTO {
    private Integer ticketId;
    private Employe employe;
    private String ticketDescription;
    private String ticketType;
    private java.sql.Date dateposted;
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
