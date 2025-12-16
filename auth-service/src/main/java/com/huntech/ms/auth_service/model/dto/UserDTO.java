package com.huntech.ms.auth_service.model.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer idUser;
    private String username;
    private String role;
}

