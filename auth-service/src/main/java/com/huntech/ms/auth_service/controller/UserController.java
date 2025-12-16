package com.huntech.ms.auth_service.controller;

import com.huntech.ms.auth_service.model.User;
import com.huntech.ms.auth_service.model.dto.UserDTO;
import com.huntech.ms.auth_service.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<User>> getAll() throws Exception{
        List<User> users = service.findAll();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception{
        User newUser = service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser (@PathVariable Integer id, @RequestBody User user) throws Exception{
        User updateUser = service.update(id, user);
         return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateUser);
    }

    @GetMapping("/by-user/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) throws Exception{
        UserDTO userDTO = mapper.map(service.findByUsername(username), UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) throws Exception{
        UserDTO dto = mapper.map(service.findById(id), UserDTO.class);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(dto);
    }

}
