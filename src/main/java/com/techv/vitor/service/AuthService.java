package com.techv.vitor.service;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.techv.vitor.controller.dto.LoginRequest;
import com.techv.vitor.controller.dto.LoginResponse;
import com.techv.vitor.entity.Roles;
import com.techv.vitor.exception.EntityNotFoundException;
import com.techv.vitor.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthService {


	private final UserRepository userRepository;
	
	private final JwtEncoder jwtEncoder;

	private final UserService userService;
	
    public AuthService(UserRepository userRepository, JwtEncoder jwtEncoder, UserService userService) {
		this.userRepository = userRepository;
		this.jwtEncoder = jwtEncoder;
		this.userService = userService;
	}


	@Transactional
    public LoginResponse auth(LoginRequest loginRequest) {

        var user = userRepository.findByUsername(loginRequest.getUsername());

        if (user.isEmpty() || !userService.verifyLogin(loginRequest)) {
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

}
