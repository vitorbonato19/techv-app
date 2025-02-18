package com.techv.vitor.service;

import com.techv.vitor.controller.dto.LoginRequest;
import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.controller.dto.UserResponseDto;
import com.techv.vitor.entity.Roles;
import com.techv.vitor.entity.Sector;
import com.techv.vitor.entity.User;
import com.techv.vitor.entity.enums.Integrated;
import com.techv.vitor.exception.EntityNotFoundException;
import com.techv.vitor.exception.PasswordOrUsernameException;
import com.techv.vitor.mapper.UserMapper;
import com.techv.vitor.repository.RoleRepository;
import com.techv.vitor.repository.SectorRepository;
import com.techv.vitor.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.Assert;

import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private LoginRequest loginRequest;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    JwtAuthenticationToken token;

    @Mock
    private JwtEncoder jwtEncoder;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper mapper;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SectorRepository sectorRepository;

    @Nested
    class findAll {

        @Test
        @DisplayName("Should return a list of users")
        void findAll() {
            User mock = new User(null, "teste", "teste@email.com", "1234", Integrated.TRUE, LocalDateTime.now());
            Mockito.when(userService.findAll()).thenReturn(Collections.singletonList(new User()));
            List<User> users = userService.findAll();

            Assertions.assertNotNull(users);
            Assertions.assertEquals(1, users.size());
        }

        @Test
        @DisplayName("Should return empty when no one users in data base")
        void shouldReturnEmtpy() {
            Mockito.when(userService.findAll()).thenReturn(Collections.emptyList());
            var response = userService.findAll();

            var empty = new ArrayList<>();

            Assertions.assertEquals(response, response);
            Assertions.assertTrue(response.isEmpty());
            Assertions.assertTrue(response.equals(empty));
        }
    }

    @Nested
    class findById {

        @Test
        @DisplayName("Should return an user by the id passed")
        void findById() {

            User user = new User(1L, "teste", "teste@email.com", "1234", Integrated.TRUE, LocalDateTime.now());

            Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
            User userResponse = userService.findById(user.getId());


            Assertions.assertNotNull(userResponse);
            Assertions.assertEquals(user.getId(), userResponse.getId());
        }

        @Test
        @DisplayName("Should throw exception when user is not find")
        void showExceptionNotFounById() {

            User user = new User(1L, "teste", "teste@email.com", "1234", Integrated.TRUE, LocalDateTime.now());

            Assertions.assertThrows(EntityNotFoundException.class, () -> userService.findById(user.getId()));
        }

    }

    @Nested
    class insertUsers {

        @Test
        @DisplayName("Should return a 201 created status code")
        void shouldReturn201Created() {

            var request = new UserRequestDto(
                    "teste",
                    "teste@email.com",
                    "1234",
                    "13082690",
                    true);

            User user = new User(1L, "teste", "teste@email.com", "1234", Integrated.TRUE, LocalDateTime.now());
            var adminRoles = new Roles(1L, "ADMIN");
            var responseDto = new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getLastModified(),
                user.getZipCode(),
                user.getRoles()
            );

            Mockito.when(mapper.toEntity(request)).thenReturn(user);
            Mockito.when(mapper.toResponseDto(user)).thenReturn(responseDto);
            Mockito.when(roleRepository.findByName("ADMIN")).thenReturn(adminRoles);
            Mockito.when(userRepository.save(user)).thenReturn(user);
            var response = userService.insertUsers(request);

            Assertions.assertNotNull(response);
            Assertions.assertEquals(user.getId(), response.getId());
            Assertions.assertEquals(user.getUsername(), response.getUsername());
            Assertions.assertEquals(user.getEmail(), response.getEmail());
        }

        @Test
        @DisplayName("Should throw a exception if any fields of request may be null")
        void throwExceptionIfFielsMayBeNull() {

            var request = new UserRequestDto(
                    "teste",
                    "teste@email.com",
                    null,
                    "13082690",
                    true);

            Assertions.assertThrows(PasswordOrUsernameException.class, () -> userService.insertUsers(request));

        }
    }

    @Nested
    class login {

        @Test
        @DisplayName("Should return true when password login are equals from the encoded user password")
        void verifiyLoginButshouldReturnTrueWhenLoginIsOk() {
            User mock = new User(1L, "Vitor", "teste@email.com", "12345", Integrated.TRUE, LocalDateTime.now());
            var request = new LoginRequest(mock.getUsername(), mock.getPassword());
            Mockito.when(userRepository.findByUsername(mock.getUsername())).thenReturn(Optional.of(mock));
            Mockito.when(userService.verifyLogin(request)).thenReturn(Boolean.TRUE);
            var response = userService.verifyLogin(request);

            Assertions.assertTrue(userService.verifyLogin(request));
        }

        @Test
        @DisplayName("Should return false when password login are different from the encoded user password")
        void verifiyLoginButshouldReturnFalseWhenPasswordLoginNotMatches() {
            User mock = new User(1L, "teste", "teste@email.com", "1234", Integrated.TRUE, LocalDateTime.now());
            Mockito.when(userRepository.findByUsername(mock.getUsername())).thenReturn(Optional.of(mock));
            var request = new LoginRequest("teste", "error");
            var response = userService.verifyLogin(request);

            Assertions.assertFalse(userService.verifyLogin(request));
        }

    }

    @Nested
    class update {


        @Test
        @DisplayName("Should update a user in database")
        void updateUser() {

            var oldUser = new User(1L, "Vitor", "vitor@test.com", "12345", Integrated.TRUE, LocalDateTime.now());
            var newUser = new User(2L, "Vitor2", "vitor@test.com", "12345", Integrated.TRUE, LocalDateTime.now());
            Mockito.when(userRepository.findById(oldUser.getId())).thenReturn(Optional.of(oldUser));
            Mockito.when(userRepository.findById(newUser.getId())).thenReturn(Optional.of(newUser));

            Mockito.when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
                User userSave = invocation.getArgument(0);
                userSave.setId(newUser.getId());
                return userSave;
            });

            userService.updateUsers(newUser, oldUser.getId());
            var responseNewUser = userRepository.findById(newUser.getId());

            Assertions.assertTrue(responseNewUser.isPresent());
        }

        @Test
        @DisplayName("Should throw a Entity Not found exception if the ID not exists in database")
        void updateUsersButThrowExceptionIfUsersNotExists() {

            User mock = new User(1L, "teste", "teste@email.com", "1234", Integrated.TRUE, LocalDateTime.now());

            Assertions.assertThrows(EntityNotFoundException.class, () -> userService.updateUsers(mock, 1L));
        }

    }

    @Nested
    class delete {


        @Test
        @DisplayName("Should delete a Entity from db")
        void deleteUserById() {

            var mock = new User(1L, "Vitor", "vitor@test.com", "12345", Integrated.TRUE, LocalDateTime.now());
            Mockito.when(userRepository.findById(mock.getId())).thenReturn(Optional.of(mock));
            Mockito.doNothing().when(userRepository).deleteById(mock.getId());

            userService.delete(mock.getId());

            Mockito.verify(userRepository, Mockito.times(1)).deleteById(mock.getId());
        }

        @Test
        @DisplayName("Should throw a Entity Not found exception if the ID not exists is database")
        void deleteUserByIdButThrowException() {
            var mock = new User(1L, "Vitor", "vitor@test.com", "12345", Integrated.TRUE, LocalDateTime.now());
            Assertions.assertThrows(
                    EntityNotFoundException.class,
                    () -> userService.delete(1L));
        }

    }
}