package com.example.projetfinal.domain;

import java.io.Serializable;

public class TickUpdatePK implements Serializable {
    private Integer ticketId;
    private Integer employeId;

    public  TickUpdatePK()
    {

    }

    public TickUpdatePK(Integer ticketID , Integer employeID)
    {
        this.employeId = employeID;
        this.ticketId = ticketID;
    }
}
