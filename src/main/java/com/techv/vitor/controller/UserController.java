package com.techv.vitor.controller;

import com.techv.vitor.controller.dto.LoginRequest;
import com.techv.vitor.controller.dto.LoginResponse;
import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.controller.dto.UserResponseDto;
import com.techv.vitor.entity.Data;
import com.techv.vitor.entity.User;
import com.techv.vitor.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final Data data;

    private final HttpHeaders headers;

    public UserController(UserService userService, Data data, HttpHeaders headers) {
        this.userService = userService;
        this.data = data;
        this.headers = headers;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Data<List<User>>> findAll(JwtAuthenticationToken token) {
        var clients = userService.findAll(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("issuer", "api-techv.java");
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        var response = new Data<>(clients, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<User>> findById(@PathVariable Long id) {
        var clients = userService.findById(id);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("issuer", "api-techv.java");
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        var response = new Data<>(clients, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<LoginResponse>> userLogin(@RequestBody LoginRequest loginRequest) {
        var responseLogin = userService.login(loginRequest);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("issuer", "api-techv.java");
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        var response = new Data<>(responseLogin, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Data<UserResponseDto>> insertUsers(@RequestBody UserRequestDto requestDto) {
        UserResponseDto dtoResponse = userService.insertUsers(requestDto);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Issuer", "api-techv.java");
        headers.add("Status", "201");
        headers.add("StatusCode", "CREATED");
        headers.add("User-Id", "" + dtoResponse.getId());
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        var response = new Data<>(dtoResponse, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<UserResponseDto>> updateUsers(@RequestBody User user, @PathVariable Long id, JwtAuthenticationToken token) {
        UserResponseDto dtoResponse = userService.updateUsers(user, id, token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Issuer", "api-techv.java");
        headers.add("Status", "200");
        headers.add("Auth-Token", "" + token.getToken());
        headers.add("StatusCode", "OK");
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        var response = new Data<>(dtoResponse, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseEntity<Void>> deleteUserById(@PathVariable Long id, JwtAuthenticationToken token) {
        userService.deleteUser(id, token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Auth-Token", "" + token.getToken());
        headers.add("issuer", "api-techv.java");
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }
}
