package com.techv.vitor.service;

import com.techv.vitor.controller.dto.LoginRequest;
import com.techv.vitor.controller.dto.LoginResponse;
import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.controller.dto.UserResponseDto;
import com.techv.vitor.entity.Cep;
import com.techv.vitor.entity.Roles;
import com.techv.vitor.entity.User;
import com.techv.vitor.exception.EntityNotFoundException;
import com.techv.vitor.exception.PasswordOrUsernameException;
import com.techv.vitor.mapper.UserMapper;
import com.techv.vitor.repository.RoleRepository;
import com.techv.vitor.repository.SectorRepository;
import com.techv.vitor.repository.TicketRepository;
import com.techv.vitor.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final TicketRepository ticketRepository;

    private final SectorRepository sectorRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtEncoder jwtEncoder;

    private final UserMapper mapper;


    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository, TicketRepository ticketRepository,
                       SectorRepository sectorRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtEncoder jwtEncoder, UserMapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.ticketRepository = ticketRepository;
        this.sectorRepository = sectorRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtEncoder = jwtEncoder;
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

    public Cep getCep(String cep) {

        RestTemplate restTemplate = new RestTemplate();
        var url = "https://opencep.com/v1/" + cep;

        ResponseEntity<Cep> response = restTemplate.getForEntity(url, Cep.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new BadCredentialsException("invalid zip code...verify you request...");
        }
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

        } catch (PasswordOrUsernameException ex) {
            throw new PasswordOrUsernameException("Existing fields that can't be null...", HttpStatus.PRECONDITION_FAILED);
        }

    }

    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {

        var user = userRepository.findByUsername(loginRequest.getUsername());

        if (user.isEmpty() || !verifyLogin(loginRequest)) {
            throw new EntityNotFoundException("Credenciais invalidas.", HttpStatus.FORBIDDEN);
        }

        var now = Instant.now();
        var expire = 150L;

        var auth  = user.get().getRoles().
                stream()
                .map(Roles::getName)
                .collect(Collectors.joining(" "));

        System.out.println(auth);

        var claims = JwtClaimsSet.builder()
                .issuer("api.java")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expire))
                .claim("auth", auth)
                .build();

        var jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        var response = new LoginResponse();

        response.setAccessToken(jwt);
        response.setExpiresIn(expire);
        response.setCreatedAt(now);

        return response;
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
    public void disable(Long id) {

        var userResponse = userRepository.findById(id);

        if (!userResponse.isPresent()) {
            throw new EntityNotFoundException("User not found...Verify the id..." + id, HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);
    }

}
