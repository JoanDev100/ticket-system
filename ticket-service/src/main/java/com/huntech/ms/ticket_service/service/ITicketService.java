package com.huntech.ms.ticket_service.service;

import com.huntech.ms.ticket_service.model.Ticket;

import java.util.List;

public interface ITicketService {
    List<Ticket> getTickets();
    Ticket save(Ticket ticket);
    Ticket update(String id, Ticket ticket);
    void delete(String id);
}
