package com.techv.vitor.service;

import com.techv.vitor.controller.dto.LoginRequest;
import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.controller.dto.UserResponseDto;
import com.techv.vitor.entity.User;
import com.techv.vitor.entity.enums.Integrated;
import com.techv.vitor.exception.EntityNotFoundException;
import com.techv.vitor.repository.UserRepository;
import jakarta.websocket.RemoteEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private LoginRequest loginRequest;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("Should return a list of users")
    void findAll() {
        User user = new User(UUID.randomUUID(), "teste", "teste@email.com", "1234", Integrated.TRUE, LocalDateTime.now());
        Mockito.when(userService.findAll()).thenReturn(Collections.singletonList(new User()));
        List<User> users = userService.findAll();

        Assertions.assertEquals(1, users.size());
    }


    @Test
    @DisplayName("Should return an user by the id passed")
    void findById() {
        UUID userid = UUID.randomUUID();
        User user = new User(userid, "vitor", "vitor@test.com", "testepassword", Integrated.TRUE, LocalDateTime.now());
        Mockito.when(userRepository.findById(userid)).thenReturn(Optional.of(user));
        Optional<User> userResponse = userRepository.findById(userid);

        Assertions.assertTrue(userResponse.isPresent());
    }


    @Test
    @DisplayName("Should throw a expection if the UUID is null")
    void findByIdButThrowEntityNotFoundExceptionIfUUIDIsNull() {
        UUID userId = null;
        var user = new User(userId, "vitor", "vitor@test.com", "testepassword", Integrated.TRUE, LocalDateTime.now());
        Assertions.assertThrows(EntityNotFoundException.class, () -> userService.findById(userId));
    }

    @Test
    @DisplayName("Should return true when password login are equals from the encoded user password")
    void verifiyLoginButshouldReturnTrueWhenLoginIsOk() {
        var mock = new User(UUID.randomUUID(), "Vitor", "vitor@test.com", "12345", Integrated.TRUE, LocalDateTime.now());
        Mockito.when(userRepository.findByUsername(mock.getUsername())).thenReturn(Optional.of(mock));
        var request = new LoginRequest("Vitor", "12345");
        Mockito.when(encoder.matches(mock.getPassword(), request.getPassword())).thenReturn(true);
        var response = userService.verifyLogin(request, encoder);

        Assertions.assertTrue(response);
    }

    @Test
    @DisplayName("Should return false when password login are different from the encoded user password")
    void verifiyLoginButshouldReturnFalseWhenPasswordLoginNotMatches() {
        var mock = new User(UUID.randomUUID(), "Vitor", "vitor@test.com", "12345", Integrated.TRUE, LocalDateTime.now());
        Mockito.when(userRepository.findByUsername(mock.getUsername())).thenReturn(Optional.of(mock));
        var request = new LoginRequest("Vitor", "12345");
        Mockito.when(encoder.matches(mock.getPassword(), request.getPassword())).thenReturn(false);
        var response = userService.verifyLogin(request, encoder);

        Assertions.assertFalse(response);
    }

    @Test
    @DisplayName("Should insert a user in database and return true if is present")
    void insertUsers() {
        var id = UUID.randomUUID();

        var userRequest = new UserRequestDto(
                "Vitor",
                "vitor@java.com",
                "12345");

        var user = new User();
        user.setId(id);
        user.setUsername("Vitor");
        user.setPassword(encoder.encode("12345"));
        user.setEmail("vitor@java.com");
        user.setLastModified(LocalDateTime.now());

        var userResponse = new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getLastModified(),
                HttpStatus.CREATED,
                HttpStatus.CREATED
        );

        Mockito.when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User userSave = invocation.getArgument(0);
            userSave.setId(id);
            return userSave;
        });
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        var responseDto = userService.insertUsers(userRequest);
        var response = userRepository.findById(responseDto.getId());

        Assertions.assertTrue(response.isPresent());
    }
}