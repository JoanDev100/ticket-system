package com.huntech.ms.ticket_service.controller;

import com.huntech.ms.ticket_service.model.Ticket;
import com.huntech.ms.ticket_service.service.ITicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TicketController {
    private final ITicketService service;

    @GetMapping
    public ResponseEntity<List<Ticket>> getAll() {
        List<Ticket> tickets = service.getTickets();
        return ResponseEntity.ok().body(tickets);
    }

//    @GetMapping("/id")
//    public ResponseEntity


}
