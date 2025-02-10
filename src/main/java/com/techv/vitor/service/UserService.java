package com.techv.vitor.service;

import com.techv.vitor.controller.dto.LoginRequest;
import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.controller.dto.UserResponseDto;
import com.techv.vitor.entity.User;
import com.techv.vitor.exception.EntityNotFoundException;
import com.techv.vitor.exception.InvalidRequestException;
import com.techv.vitor.exception.PasswordOrUsernameException;
import com.techv.vitor.mapper.UserMapper;
import com.techv.vitor.repository.RoleRepository;
import com.techv.vitor.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserMapper mapper;

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mapper = mapper;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllHealthCheck() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(
                () -> new EntityNotFoundException("User not found...Verify the id:  " +
                        id, HttpStatus.NOT_FOUND)
        );
    }

    @Transactional
    public UserResponseDto insertUsers(UserRequestDto requestDto) {

        try {

            if (requestDto.getUsername() == null ||
                    requestDto.getPassword() == null ||
                    requestDto.getEmail() == null ||
                    requestDto.getZipCode() == null) {
                throw new PasswordOrUsernameException(
                        "Existing fields that can't be null...",
                        HttpStatus.PRECONDITION_FAILED);
            }

            User user = mapper.toEntity(requestDto);
            user.setLastModified(LocalDateTime.now());

            if (requestDto.isAdmin()) {
                user.setRoles(Set.of((roleRepository.findByName("ADMIN"))));
            } else {
                user.setRoles(Set.of((roleRepository.findByName("NOT_ADMIN"))));
            }

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            userRepository.save(user);

            var responseDto = mapper.toResponseDto(user);

            return responseDto;

        } catch (InvalidRequestException ex) {
            throw new InvalidRequestException(ex.getMessage(), ex.getHttpStatus());
        }

    }



    @Transactional
    public Boolean verifyLogin(LoginRequest loginRequest) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(
                () -> new EntityNotFoundException("User not found, verify your request", HttpStatus.NOT_FOUND)
        ));

        return user.filter(response -> bCryptPasswordEncoder
                .matches(
                        loginRequest.getPassword(), user.get().getPassword())).isPresent();
    }


    @Transactional
    public void updateUsers(User user, Long id) {

        User newUser = findById(id);
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setTickets(user.getTickets());

        userRepository.save(newUser);
    }


    @Transactional
    public void delete(Long id) {

        var userResponse = userRepository.findById(id);

        if (!userResponse.isPresent()) {
            throw new EntityNotFoundException("User not found...Verify the id..." + id, HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);
    }

}
