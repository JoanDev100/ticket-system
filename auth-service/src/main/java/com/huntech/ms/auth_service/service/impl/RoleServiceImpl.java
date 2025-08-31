package com.huntech.ms.auth_service.service.impl;

import com.huntech.ms.auth_service.model.Role;
import com.huntech.ms.auth_service.repository.RoleRepository;
import com.huntech.ms.auth_service.service.IRoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final RoleRepository repository;
    @Override
    public Role save(Role role) throws Exception {
        Role newRole = Role.builder()
                .idRole(role.getIdRole())
                .name(role.getName())
                .enabled(role.isEnabled())
                .build();
        return repository.save(newRole);
    }

    @Override
    public Role update(Long id, Role role) throws Exception {
        Role existingRole = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("NOT FOUND ID: " + id));
        Role updatedRole = existingRole.toBuilder()
                .name(role.getName())
                .enabled(role.isEnabled())
                .build();
        return repository.save(updatedRole);
    }

    @Override
    public List<Role> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Role findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("NOT FOUND ID: " + id));
    }

    @Override
    public void delete(Long id) throws Exception {
        repository.deleteById(id);
    }
}
