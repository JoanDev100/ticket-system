package com.huntech.ms.auth_service.controller;

import com.huntech.ms.auth_service.model.User;
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

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) throws Exception{
        User user = service.findById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok().body(user);
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

}
