package com.huntech.ms.auth_service.service.impl;

import com.huntech.ms.auth_service.model.Role;
import com.huntech.ms.auth_service.model.User;
import com.huntech.ms.auth_service.repository.UserRepository;
import com.huntech.ms.auth_service.service.IRoleService;
import com.huntech.ms.auth_service.service.IUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;
    private final IRoleService service;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) throws Exception {
        Role role = service.findById(user.getRole().getIdRole());

        User newUser = User.builder()
                .idUser(user.getIdUser())
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .enabled(user.isEnabled())
                .role(role)
                .build();

        return repository.save(newUser);
    }

    @Override
    public User update(Integer id, User userDetails) throws Exception {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NOT FOUND ID: " + id));

        User updatedUser = existingUser.toBuilder()
                .username(userDetails.getUsername())
                .password(userDetails.getPassword() != null ?
                        passwordEncoder.encode(userDetails.getPassword()) :
                        existingUser.getPassword())
                .enabled(userDetails.isEnabled())
                .build();

        return repository.save(updatedUser);
    }

    @Override
    public List<User> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public User findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("NOT FOUND ID: " + id));
        
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.deleteById(id);
    }
}
