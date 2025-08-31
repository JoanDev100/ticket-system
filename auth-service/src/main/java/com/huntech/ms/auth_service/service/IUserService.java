package com.huntech.ms.auth_service.service;

import com.huntech.ms.auth_service.model.User;

import java.util.List;

public interface IUserService {
    User save(User user) throws Exception;
    User update(Integer id, User user) throws Exception;
    List<User> findAll() throws Exception;
    User findById(Integer id) throws Exception;
    void delete(Integer id) throws Exception;
}
