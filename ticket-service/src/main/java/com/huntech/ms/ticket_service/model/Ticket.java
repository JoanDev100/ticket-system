package com.huntech.ms.ticket_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    private String id;

    private String title;
    private String description;

    private String status; // OPEN, IN_PROGRESS, RESOLVED, CLOSED
    private String priority; // LOW, MEDIUM, HIGH, URGENT
    private String category; // TECHNICAL, BILLING, GENERAL

    private Integer createdById; // User ID from auth-service
    private String createdByUsername;

    private Integer assignedToId; // User ID from auth-service (optional)
    private String assignedToUsername;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime closedAt;

    private List<String> tags;

    // Embedded documents
//    private List<Attachment> attachments;
//    private List<Comment> comments;
}
