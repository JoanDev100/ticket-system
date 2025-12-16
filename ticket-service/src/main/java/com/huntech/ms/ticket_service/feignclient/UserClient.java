package com.huntech.ms.ticket_service.feignclient;

import com.huntech.ms.ticket_service.model.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name= "auth-service", url= "http://localhost:8081/user")
public interface UserClient {

    @GetMapping("/by-user/{username}")
    public UserDTO getUserByUsername(
            @PathVariable("username") String username);

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Integer id);

}
