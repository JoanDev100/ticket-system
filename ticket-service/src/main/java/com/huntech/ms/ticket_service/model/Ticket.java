package com.huntech.ms.ticket_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "tickets")
@Data
public class Ticket {

    @Id
    private String id;

    private String title;
    private String description;

    private String status; // OPEN, IN_PROGRESS, RESOLVED, CLOSED
    private String priority; // LOW, MEDIUM, HIGH, URGENT
    private String category; // TECHNICAL, BILLING, GENERAL

    private String createdBy; // User ID from auth-service
    private String assignedTo; // User ID from auth-service (optional)

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime closedAt;

    private List<String> tags;

    // Embedded documents
//    private List<Attachment> attachments;
//    private List<Comment> comments;
}
