package com.huntech.ms.ticket_service.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TicketResponseDTO {
    private String title;
    private String description;

    private String status;
    private String priority;
    private String category;

    private Integer createdById;
    private String createdByUsername;
    private String createdByRole; // Nuevo campo para el nombre del rol

    private Integer assignedToId;
    private String assignedToUsername;
    private String assignedToRole; // Nuevo campo para el nombre del rol

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime closedAt;

    private List<String> tags;
}

