package com.huntech.ms.auth_service.service;

import com.huntech.ms.auth_service.model.Role;

import java.util.List;

public interface IRoleService {
    Role save(Role role) throws Exception;
    Role update(Long id, Role role) throws Exception;
    List<Role> findAll() throws Exception;
    Role findById(Long id) throws Exception;
    void delete(Long id) throws Exception;
}
