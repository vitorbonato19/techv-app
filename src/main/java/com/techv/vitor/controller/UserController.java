package com.techv.vitor.controller;

import com.techv.vitor.controller.dto.LoginRequest;
import com.techv.vitor.controller.dto.LoginResponse;
import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.controller.dto.UserResponseDto;
import com.techv.vitor.entity.Data;
import com.techv.vitor.entity.User;
import com.techv.vitor.service.UserService;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

@RestController
@RequestMapping("/users")
public class UserController {

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final Data data;

    private final HttpHeaders headers;

    public UserController(UserService userService, Data data, HttpHeaders headers) {
        this.userService = userService;
        this.data = data;
        this.headers = headers;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<List<User>>> findAll(@RequestHeader("Authorization") String token) {
        var clients = userService.findAll();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setDate(Instant.now());
        headers.setAccessControlAllowMethods(List.of(HttpMethod.GET));
        var headerData = data.convertToMap(headers);
        var response = new Data<>(clients, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<User>> findById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        var clients = userService.findById(id);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setDate(Instant.now());
        headers.setAccessControlAllowMethods(List.of(HttpMethod.GET));
        var headerData = data.convertToMap(headers);
        var response = new Data<>(clients, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Data<UserResponseDto>> insertUsers(@RequestBody UserRequestDto requestDto) {
        UserResponseDto dtoResponse = userService.insertUsers(requestDto);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setDate(Instant.now());
        headers.setAccessControlAllowMethods(List.of(HttpMethod.POST));
        var headerData = data.convertToMap(headers);
        var response = new Data<>(dtoResponse, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<Void>> updateUsers(@RequestHeader("Authorization") String token, @RequestBody User user, @PathVariable Long id) {
        userService.updateUsers(user, id);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setDate(Instant.now());
        headers.setAccessControlAllowMethods(List.of(HttpMethod.PUT));
        var headerData = data.convertToMap(headers);
        var response = new Data<>(null, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Data<String>> deleteUserById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        userService.disable(id);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setDate(Instant.now());
        headers.setAccessControlAllowMethods(List.of(HttpMethod.DELETE));
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        var response = new Data<>("NO-CONTENT", headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.NO_CONTENT);
    }
}
