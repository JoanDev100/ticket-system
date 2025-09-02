package com.huntech.ms.auth_service.service;

import com.huntech.ms.auth_service.model.User;
import com.huntech.ms.auth_service.repository.UserRepository;
import com.huntech.ms.auth_service.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    void test_FindAllUsers() throws Exception {
        //Given
        List<User> expectedList = List.of(
//                new User(1, "usertest1", "passtest1", true),
//                new User(2, "usertest2", "passtest2", false),
//                new User(3, "usertest3", "passtest3", true),
//                new User(4, "usertest4", "passtest4", false)
                );
        when(repository.findAll()).thenReturn(expectedList);
        //When
        List<User> users = service.findAll();
        //Then
        assertThat(expectedList).isEqualTo(users);
        verify(repository,times(1)).findAll();

    }

    @Test
    void test_SaveUser() throws Exception {
        //GIVEN
        User userExpected = new User();
        when(repository.save(userExpected)).thenReturn(userExpected);
        //WHEN
        User savedUser = service.save(userExpected);
        //THEN
        verify(repository,times(1)).save(userExpected);
        assertThat(userExpected).isEqualTo(savedUser);

    }

}
