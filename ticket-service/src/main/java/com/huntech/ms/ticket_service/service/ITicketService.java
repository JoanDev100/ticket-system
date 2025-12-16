package com.huntech.ms.ticket_service.service;

import com.huntech.ms.ticket_service.model.Ticket;
import com.huntech.ms.ticket_service.model.dto.TicketResponseDTO;

import java.util.List;

public interface ITicketService {
    List<TicketResponseDTO> getTicketsDTO();
    Ticket save(Ticket ticket);
    Ticket update(String id, Ticket ticket);
    void delete(String id);
    Ticket createTicket2(String creatorUsername, Ticket ticketData);
    TicketResponseDTO convertTicketToDTOResponse(Ticket ticket);
}
