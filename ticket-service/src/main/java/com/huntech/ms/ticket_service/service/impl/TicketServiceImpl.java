package com.huntech.ms.ticket_service.service.impl;

import com.huntech.ms.ticket_service.feignclient.UserClient;
import com.huntech.ms.ticket_service.model.Ticket;
import com.huntech.ms.ticket_service.model.dto.TicketResponseDTO;
import com.huntech.ms.ticket_service.model.dto.UserDTO;
import com.huntech.ms.ticket_service.repository.ITicketRepo;
import com.huntech.ms.ticket_service.service.ITicketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements ITicketService {

    private final ITicketRepo repo;
    private final UserClient userClient;
    private final ModelMapper defaultMapper;

    @Override
    public List<TicketResponseDTO> getTicketsDTO() {
        return repo.findAll().stream()
                .map(this::convertTicketToDTOResponse)
                .toList();
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

    // -----------------------------
    // Conversión de Ticket → TicketResponseDTO
    // -----------------------------
    public TicketResponseDTO convertTicketToDTOResponse(Ticket ticket) {

        // 1️⃣ Mapear los campos básicos del ticket → DTO
        TicketResponseDTO response = defaultMapper.map(ticket, TicketResponseDTO.class);

        // 2️⃣ Agregar datos del usuario creador
        if (ticket.getCreatedById() != null) {
            UserDTO createdUser = userClient.getUserById(ticket.getCreatedById());
            response.setCreatedByRole(createdUser.getRole()); // <-- acá el nombre del rol
        }

        // 3️⃣ Agregar datos del usuario asignado
        if (ticket.getAssignedToId() != null) {
            UserDTO assignedUser = userClient.getUserById(ticket.getAssignedToId());
            response.setAssignedToRole(assignedUser.getRole()); // <-- acá el nombre del rol
        }

        return response;
    }

    public Ticket createTicket2(String creatorUsername, Ticket ticketData) {
        // 1️⃣ Buscar usuario creador
        UserDTO creator = userClient.getUserByUsername(creatorUsername);

        // 2️⃣ Determinar usuario asignado
        UserDTO assignedUser;
        if (ticketData.getAssignedToId() == null) {
            assignedUser = creator;
        } else {
            assignedUser = userClient.getUserById(ticketData.getAssignedToId());
        }

        // 3️⃣ Crear ticket
        Ticket ticket = new Ticket();
        ticket.setTitle(ticketData.getTitle());
        ticket.setDescription(ticketData.getDescription());
        ticket.setStatus("OPEN");
        ticket.setPriority(ticketData.getPriority() != null ? ticketData.getPriority() : "MEDIUM");
        ticket.setCategory(ticketData.getCategory() != null ? ticketData.getCategory() : "GENERAL");
        ticket.setTags(ticketData.getTags() != null ? ticketData.getTags() : new ArrayList<>());

        ticket.setCreatedById(creator.getIdUser());
        ticket.setCreatedByUsername(creator.getUsername());
        ticket.setAssignedToId(assignedUser.getIdUser());
        ticket.setAssignedToUsername(assignedUser.getUsername());

        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());
        ticket.setClosedAt(null);

        // 4️⃣ Guardar en MongoDB
        return repo.save(ticket);
    }

}
