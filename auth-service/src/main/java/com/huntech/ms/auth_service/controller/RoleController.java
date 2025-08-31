package com.huntech.ms.auth_service.controller;

import com.huntech.ms.auth_service.model.Role;
import com.huntech.ms.auth_service.model.Role;
import com.huntech.ms.auth_service.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService service;
    
    @GetMapping
    public ResponseEntity<List<Role>> getAll() throws Exception{
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok().body(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity<Role> saveRole(@RequestBody Role obj) throws Exception{
        Role newObj = service.save(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(newObj);
    }

    @PutMapping("{id}")
    public ResponseEntity<Role> updateRole (@PathVariable Long id, @RequestBody Role role) throws Exception{
        Role updateRole = service.update(id, role);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateRole);
    }
    
}
