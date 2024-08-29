package com.techv.vitor.service;

import com.techv.vitor.controller.dto.LoginRequest;
import com.techv.vitor.controller.dto.LoginResponse;
import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.controller.dto.UserResponseDto;
import com.techv.vitor.entity.User;
import com.techv.vitor.exception.EntityNotFoundException;
import com.techv.vitor.exception.PasswordOrUsernameException;
import com.techv.vitor.repository.TicketRepository;
import com.techv.vitor.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final TicketRepository ticketRepository;

    private final BCryptPasswordEncoder encoder;

    private final JwtEncoder jwtEncoder;

    public UserService(UserRepository userRepository,
                       TicketRepository ticketRepository,
                       BCryptPasswordEncoder encoder,
                       JwtEncoder jwtEncoder) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
        this.encoder = encoder;
        this.jwtEncoder = jwtEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(
                () -> new EntityNotFoundException("User not found...Verify the id:  " +
                        id, HttpStatus.NOT_FOUND)
        );
    }

    @Transactional
    public UserResponseDto insertUsers(UserRequestDto requestDto) {

        if (requestDto.getUsername() == null ||
                requestDto.getPassword() == null ||
                    requestDto.getEmail() == null) {
            throw new PasswordOrUsernameException(
                    "Existing fields that can't be null...",
                    HttpStatus.PRECONDITION_FAILED);
        }

        try {

            User user = new User();

            user.setUsername(requestDto.getUsername());
            user.setPassword(encoder.encode(requestDto.getPassword()));
            user.setEmail(requestDto.getEmail());
            user.setLastModified(LocalDateTime.now());

            userRepository.save(user);

            UserResponseDto responseDto = new UserResponseDto();

            responseDto.setId(user.getId());
            responseDto.setUsername(user.getUsername());
            responseDto.setEmail(user.getEmail());
            responseDto.setPassword(user.getPassword());
            responseDto.setLastModified(LocalDateTime.now());
            responseDto.setStatus(HttpStatus.CREATED);
            responseDto.setStatusCode(HttpStatus.CREATED);

            return responseDto;

        } catch (PasswordOrUsernameException ex) {
            throw new PasswordOrUsernameException("Existing fields that can't be null...", HttpStatus.PRECONDITION_FAILED);
        }

    }

    @Transactional
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {

        var user = userRepository.findByUsername(loginRequest.getUsername());

        if (user.isEmpty() || !verifyLogin(loginRequest, encoder)) {
            throw new BadCredentialsException("user login is invalid...verify the login credentials.");
        }

        Instant now = Instant.now();
        Long expiresIn = 150L;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("api.java")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        var response = new LoginResponse();

        response.setAccessToken(jwtValue);
        response.setExpiresIn(expiresIn);
        response.setCreatedAt(now);

        return ResponseEntity.ok().body(response);
    }


    @Transactional
    public Boolean verifyLogin(LoginRequest loginRequest, PasswordEncoder encoder) {
        var user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(
                        () -> new EntityNotFoundException("User not found...Verify the username: " +
                                loginRequest.getUsername(),
                                HttpStatus.NOT_FOUND)
                );
        return encoder.matches(loginRequest.getPassword(), user.getPassword());
    }


    @Transactional
    public UserResponseDto updateUsers(User user, UUID id) {

        User newUser = findById(id);
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setTickets(user.getTickets());

        userRepository.save(newUser);

        UserResponseDto responseDto = new UserResponseDto();

        responseDto.setId(newUser.getId());
        responseDto.setUsername(newUser.getUsername());
        responseDto.setEmail(newUser.getEmail());
        responseDto.setPassword(newUser.getPassword());
        responseDto.setLastModified(LocalDateTime.now());
        responseDto.setStatus(HttpStatus.OK);

        return responseDto;
    }


    @Transactional
    public void deleteUser(UUID id) {

        var userResponse = userRepository.findById(id);

        if (!userResponse.isPresent()) {
            throw new EntityNotFoundException("User not found...Verify the id..." + id, HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);
    }
}
