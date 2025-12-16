package com.huntech.ms.ticket_service.controller;

import com.huntech.ms.ticket_service.model.Ticket;
import com.huntech.ms.ticket_service.model.dto.TicketResponseDTO;
import com.huntech.ms.ticket_service.security.JwtUtil;
import com.huntech.ms.ticket_service.service.ITicketService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final ITicketService service;
    private final JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> getAllTickets() {
        List<TicketResponseDTO> tickets = service.getTicketsDTO();
        return ResponseEntity.ok().body(tickets);
    }

//    @GetMapping("/id")
//    public ResponseEntity

    @PostMapping("/create")
    public ResponseEntity<Ticket> createTicket(
            @RequestBody Ticket ticketRequest,
            HttpServletRequest request
    ) {
        try {
            // 1️⃣ Obtener token del header
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (header == null || !header.startsWith("Bearer ")) {
                return ResponseEntity.status(401).build();
            }

            String token = header.substring(7); // Quitar "Bearer "
            // 2️⃣ Extraer username del JWT
            String creatorUsername = jwtUtil.getUsernameFromToken(token);

            // 3️⃣ Crear ticket
            Ticket newTicket = service.createTicket2(creatorUsername, ticketRequest);

            return ResponseEntity.ok(newTicket);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
