package com.huntech.ms.ticket_service.service.impl;

import com.huntech.ms.ticket_service.model.Ticket;
import com.huntech.ms.ticket_service.repository.ITicketRepo;
import com.huntech.ms.ticket_service.service.ITicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements ITicketService {
    private final ITicketRepo repo;

    @Override
    public List<Ticket> getTickets() {
        return repo.findAll();
    }

    @Override
    public Ticket save(Ticket ticket) {
        return repo.save(ticket);
    }

    @Override
    public Ticket update(String id, Ticket ticketDetails) {
        return repo.findById(id)
                .map(ticket -> {
                    if (ticketDetails.getTitle() != null)
                        ticket.setTitle(ticketDetails.getTitle());
                    if (ticketDetails.getDescription() != null)
                        ticket.setDescription(ticketDetails.getDescription());
                    if (ticketDetails.getStatus() != null)
                        ticket.setStatus(ticketDetails.getStatus());

                    ticket.setUpdatedAt(LocalDateTime.now());
                    return repo.save(ticket);
                })
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }
}
