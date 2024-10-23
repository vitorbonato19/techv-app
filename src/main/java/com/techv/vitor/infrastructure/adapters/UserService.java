package com.techv.vitor.infrastructure.adapters;

import com.techv.vitor.application.entity.User;
import com.techv.vitor.application.usecases.UserUseCasesImpl;
import com.techv.vitor.controller.dto.LoginRequest;
import com.techv.vitor.controller.dto.LoginResponse;
import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.controller.dto.UserResponseDto2;
import com.techv.vitor.infrastructure.dto.UserResponseDto;
import com.techv.vitor.infrastructure.exception.EntityNotFoundException;
import com.techv.vitor.infrastructure.exception.PasswordOrUsernameException;
import com.techv.vitor.infrastructure.mapper.UserEntityMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserUseCasesImpl userUseCases;

    private final UserEntityMapper userEntityMapper;

    public UserService(UserUseCasesImpl userUseCases, UserEntityMapper userEntityMapper) {
        this.userUseCases = userUseCases;
        this.userEntityMapper = userEntityMapper;
    }

    public List<UserResponseDto> findAll(int page,
                                         int size,
                                         JwtAuthenticationToken token) {
        var entity =
                userUseCases.findAll(page, size).stream()
                
        return userEntityMapper.toResponseDto();
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
    public UserResponseDto2 insertUsers(UserRequestDto requestDto) {

        try {

            if (requestDto.getUsername() == null ||
                    requestDto.getPassword() == null ||
                    requestDto.getEmail() == null ||
                    requestDto.getZipCode() == null) {
                throw new PasswordOrUsernameException(
                        "Existing fields that can't be null...",
                        HttpStatus.PRECONDITION_FAILED);
            }

            var user = mapper.toEntity(requestDto);

            user.setRoles(Set.of(roleRepository.findByName(Roles.Values.ADMIN.name())));
            user.setSector(Set.of(sectorRepository.findByName(Sector.Values.TI.name())));

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
        return loginRequest.getPassword().equals(user.getPassword());
    }


    @Transactional
    public UserResponseDto2 updateUsers(User user, Long id , JwtAuthenticationToken token) {

        User newUser = findById(id);
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setTickets(user.getTickets());

        userRepository.save(newUser);

        var responseDto = mapper.toResponseDto(newUser);

        return responseDto;
    }


    @Transactional
    public void deleteUser(Long id, JwtAuthenticationToken token) {

        var userResponse = userRepository.findById(id);

        if (!userResponse.isPresent()) {
            throw new EntityNotFoundException("User not found...Verify the id..." + id, HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);
    }

}