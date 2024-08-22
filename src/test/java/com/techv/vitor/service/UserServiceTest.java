package com.techv.vitor.service;

import com.techv.vitor.controller.dto.UserResponseDto;
import com.techv.vitor.entity.User;
import com.techv.vitor.entity.enums.Admin;
import com.techv.vitor.entity.enums.Integrated;
import com.techv.vitor.exception.EntityNotFoundException;
import com.techv.vitor.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("Should return a list of users")
    void findAll() {
        User user = new User(UUID.randomUUID(), "teste", "teste@email.com", "1234", Integrated.TRUE, LocalDateTime.now(), Admin.ADMIN);
        Mockito.when(userService.findAll()).thenReturn(Collections.singletonList(new User()));
        List<User> users = userService.findAll();

        Assertions.assertEquals(1, users.size());
    }


    @Test
    @DisplayName("Should return an user by the id passed")
    void findById() {
        UUID userid = UUID.randomUUID();
        User user = new User(userid, "vitor", "vitor@test.com", "testepassword", Integrated.TRUE, LocalDateTime.now(), Admin.ADMIN);
        Mockito.when(userRepository.findById(userid)).thenReturn(Optional.of(user));
        Optional<User> userResponse = userRepository.findById(userid);

        Assertions.assertTrue(userResponse.isPresent());
    }


    @Test
    @DisplayName("Should throw a expection if the UUID is null")
    void findByIdButThrowEntityNotFoundExceptionIfUUIDIsNull() {
        UUID userId = null;
        User user = new User(userId, "vitor", "vitor@test.com", "testepassword", Integrated.TRUE, LocalDateTime.now(), Admin.ADMIN);
        Assertions.assertThrows(EntityNotFoundException.class, () -> userService.findById(userId));
    }
}