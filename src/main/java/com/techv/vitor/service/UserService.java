package com.techv.vitor.service;

import com.techv.vitor.controller.dto.LoginRequest;
import com.techv.vitor.controller.dto.LoginResponse;
import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.controller.dto.UserResponseDto;
import com.techv.vitor.entity.Cep;
import com.techv.vitor.entity.Roles;
import com.techv.vitor.entity.Sector;
import com.techv.vitor.entity.User;
import com.techv.vitor.exception.EntityNotFoundException;
import com.techv.vitor.exception.PasswordOrUsernameException;
import com.techv.vitor.repository.RoleRepository;
import com.techv.vitor.repository.SectorRepository;
import com.techv.vitor.repository.TicketRepository;
import com.techv.vitor.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final TicketRepository ticketRepository;

    private final SectorRepository sectorRepository;

    private final JwtEncoder jwtEncoder;


    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository, TicketRepository ticketRepository,
                       SectorRepository sectorRepository, JwtEncoder jwtEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.ticketRepository = ticketRepository;
        this.sectorRepository = sectorRepository;
        this.jwtEncoder = jwtEncoder;
    }

    public List<User> findAll() {
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
            throw new BadCredentialsException("invalid cep...verify the cep...");
        }
    }

    @Transactional
    public UserResponseDto insertUsers(UserRequestDto requestDto) {

        try {

            if (requestDto.getUsername() == null ||
                    requestDto.getPassword() == null ||
                    requestDto.getEmail() == null ||
                    requestDto.getCep() == null) {
                throw new PasswordOrUsernameException(
                        "Existing fields that can't be null...",
                        HttpStatus.PRECONDITION_FAILED);
            }

            var getCep = getCep(requestDto.getCep());
            var role = roleRepository.findByName(Roles.Values.NOT_ADMIN.name());
            var sector = sectorRepository.findByName(Sector.Values.TI.name());

            User user = new User();

            user.setUsername(requestDto.getUsername());
            user.setPassword(requestDto.getPassword());
            user.setEmail(requestDto.getEmail());
            user.setLastModified(LocalDateTime.now());
            user.setSector(Set.of(sector));
            user.setRoles(Set.of(role));

            userRepository.save(user);

            UserResponseDto responseDto = new UserResponseDto();

            responseDto.setId(user.getId());
            responseDto.setUsername(user.getUsername());
            responseDto.setEmail(user.getEmail());
            responseDto.setPassword(user.getPassword());
            responseDto.setLastModified(LocalDateTime.now());
            responseDto.setStatus(HttpStatus.CREATED);
            responseDto.setStatusCode(HttpStatus.CREATED);
            responseDto.setCep(getCep);

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

        Instant now = Instant.now();
        Long expiresIn = 150L;

        var admin = userRepository.findAdmin(user.get().getId());

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("api.java")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", admin)
                .build();

        var jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        var response = new LoginResponse();

        response.setAccessToken(jwt);
        response.setExpiresIn(expiresIn);
        response.setCreatedAt(now);

        return response;
    }


    @Transactional
    public Boolean verifyLogin(LoginRequest loginRequest) {
        var user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(
                        () -> new EntityNotFoundException("User not found...Verify the username: " +
                                loginRequest.getUsername(),
                                HttpStatus.NOT_FOUND)
                );
        return loginRequest.getPassword().equals(user.getPassword()) ? true : false;
    }


    @Transactional
    public UserResponseDto updateUsers(User user, Long id) {

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
    public void deleteUser(Long id) {

        var userResponse = userRepository.findById(id);

        if (!userResponse.isPresent()) {
            throw new EntityNotFoundException("User not found...Verify the id..." + id, HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);
    }
}
