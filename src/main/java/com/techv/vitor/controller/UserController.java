package com.techv.vitor.controller;

import com.techv.vitor.controller.dto.LoginRequest;
import com.techv.vitor.controller.dto.LoginResponse;
import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.controller.dto.UserResponseDto;
import com.techv.vitor.entity.Data;
import com.techv.vitor.entity.User;
import com.techv.vitor.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
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
    public ResponseEntity<Data<List<User>>> findAll() {
        var clients = userService.findAll();
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

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<LoginResponse>> userLogin(@RequestBody LoginRequest loginRequest) {
        var login = userService.login(loginRequest);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("issuer", "api-techv.java");
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        var response = new Data<>(login, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Data<UserResponseDto>> insertUsers(@RequestBody UserRequestDto requestDto) {
        UserResponseDto dtoResponse = userService.insertUsers(requestDto);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("issuer", "api-techv.java");
        headers.add("userId", "" + dtoResponse.getId());
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        var response = new Data<>(dtoResponse, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<UserResponseDto>> updateUsers(@RequestBody User user, @PathVariable Long id) {
        UserResponseDto dtoResponse = userService.updateUsers(user, id);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("issuer", "api-techv.java");
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        var response = new Data<>(dtoResponse, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseEntity<Void>> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("issuer", "api-techv.java");
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }
}
