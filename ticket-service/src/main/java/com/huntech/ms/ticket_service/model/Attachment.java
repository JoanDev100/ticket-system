package com.huntech.ms.ticket_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Attachment {
    private String fileName;
    private String fileUrl;
    private Long fileSize;
    private LocalDateTime uploadedAt;
}
