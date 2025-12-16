package com.huntech.ms.ticket_service.exception;

import java.time.LocalDateTime;

public record CustomErrorResponse(
        LocalDateTime datetime,
        String message,
        String path) { }
