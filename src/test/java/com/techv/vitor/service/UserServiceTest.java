package com.techv.vitor.service;

import com.techv.vitor.controller.dto.LoginRequest;
import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.entity.Cep;
import com.techv.vitor.entity.User;
import com.techv.vitor.entity.enums.Integrated;
import com.techv.vitor.exception.EntityNotFoundException;
import com.techv.vitor.exception.PasswordOrUsernameException;
import com.techv.vitor.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private LoginRequest loginRequest;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private JwtEncoder jwtEncoder;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("Should return a list of users")
    void findAll() {
        User user = new User(1L, "teste", "teste@email.com", "1234", Integrated.TRUE, LocalDateTime.now());
        Mockito.when(userService.findAll()).thenReturn(Collections.singletonList(new User()));
        List<User> users = userService.findAll();

        Assertions.assertEquals(1, users.size());
    }


    @Test
    @DisplayName("Should return an user by the id passed")
    void findById() {
        User user = new User(1L, "teste", "teste@email.com", "1234", Integrated.TRUE, LocalDateTime.now());
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Optional<User> userResponse = userRepository.findById(user.getId());

        Assertions.assertTrue(userResponse.isPresent());
    }


    @Test
    @DisplayName("Should throw an exception if the UUID was null")
    void findByIdButThrowEntityNotFoundExceptionIfUUIDIsNull() {
        User user = new User(1L, "teste", "teste@email.com", "1234", Integrated.TRUE, LocalDateTime.now());
        Assertions.assertThrows(EntityNotFoundException.class, () -> userService.findById(null));
    }

    @Test
    @DisplayName("Should return true when password login are equals from the encoded user password")
    void verifiyLoginButshouldReturnTrueWhenLoginIsOk() {
        User mock = new User(1L, "teste", "teste@email.com", "1234", Integrated.TRUE, LocalDateTime.now());
        Mockito.when(userRepository.findByUsername(mock.getUsername())).thenReturn(Optional.of(mock));
        var request = new LoginRequest("Vitor", "12345");
        Mockito.when(encoder.matches(mock.getPassword(), request.getPassword())).thenReturn(true);
        var response = userService.verifyLogin(request, encoder);

        Assertions.assertTrue(response);
    }

    @Test
    @DisplayName("Should return false when password login are different from the encoded user password")
    void verifiyLoginButshouldReturnFalseWhenPasswordLoginNotMatches() {
        User mock = new User(1L, "teste", "teste@email.com", "1234", Integrated.TRUE, LocalDateTime.now());
        Mockito.when(userRepository.findByUsername(mock.getUsername())).thenReturn(Optional.of(mock));
        var request = new LoginRequest("Vitor", "12345");
        Mockito.when(encoder.matches(mock.getPassword(), request.getPassword())).thenReturn(false);
        var response = userService.verifyLogin(request, encoder);

        Assertions.assertFalse(response);
    }

    @Test
    @DisplayName("Should insert an user in database and return true if he's present")
    void insertUsers() {

        var userRequest = new UserRequestDto(
                "Vitor",
                "vitor@java.com",
                "12345",
                "13082690");

        var user = new User();
        user.setId(1L);
        user.setUsername(userRequest.getUsername());
        user.setPassword(encoder.encode(userRequest.getPassword()));
        user.setEmail(userRequest.getEmail());
        user.setLastModified(LocalDateTime.now());


        Mockito.when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User userSave = invocation.getArgument(0);
            userSave.setId(user.getId());
            return userSave;
        });
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        var responseDto = userService.insertUsers(userRequest);
        var response = userRepository.findById(responseDto.getId());

        Assertions.assertTrue(response.isPresent());
    }

    @Test
    @DisplayName("Should throw an exception if username, password or email are null")
    void insertUsersButThrowExceptionIf() {

        var userRequest = new UserRequestDto(
                null,
                "vitor@java.com",
                "12345",
                "13082690");

        var user = new User();
        user.setId(1L);
        user.setUsername(userRequest.getUsername());
        user.setPassword(encoder.encode(userRequest.getPassword()));
        user.setEmail(userRequest.getPassword());
        user.setLastModified(LocalDateTime.now());

        Assertions.assertThrows(PasswordOrUsernameException.class, () -> userService.insertUsers(userRequest));
    }

    @Test
    @DisplayName("Should throw a Entity Not found exception if the UUID not exists is database")
    void updateUsersButThrowExceptionIfUsersNotExists() {

        User mock = new User(1L, "teste", "teste@email.com", "1234", Integrated.TRUE, LocalDateTime.now());
        Mockito.when(userRepository.findById(mock.getId())).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> userRepository.findById(mock.getId()));
    }

    @Test
    @DisplayName("Should update a user in database")
    void updateUser() {


        var mock = new User(1L, "Vitor", "vitor@test.com", "12345", Integrated.TRUE, LocalDateTime.now());
        var newUser = new User(2L, "Vitor2", "vitor@test.com", "12345", Integrated.TRUE, LocalDateTime.now());
        Mockito.when(userRepository.findById(mock.getId())).thenReturn(Optional.of(mock));
        Mockito.when(userRepository.findById(newUser.getId())).thenReturn(Optional.of(newUser));

        Mockito.when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User userSave = invocation.getArgument(0);
            userSave.setId(newUser.getId());
            return userSave;
        });

        var responseMock = userRepository.findById(mock.getId());
        var responseNewUser = userRepository.findById(newUser.getId());

        Assertions.assertNotNull(userService.updateUsers(newUser, mock.getId()));
        Assertions.assertTrue(responseMock.isPresent());
        Assertions.assertTrue(responseNewUser.isPresent());

        Mockito.verify(userRepository, Mockito.times(1)).save(newUser);

    }

    @Test
    @DisplayName("Should throw a Entity Not found exception if the UUID not exists is database")
    void deleteUserById() {

        var mock = new User(1L, "Vitor", "vitor@test.com", "12345", Integrated.TRUE, LocalDateTime.now());
        Mockito.when(userRepository.findById(mock.getId())).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> userRepository.findById(mock.getId()));
    }

}