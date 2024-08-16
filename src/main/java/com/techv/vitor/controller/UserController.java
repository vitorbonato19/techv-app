package com.techv.vitor.controller;

import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.controller.dto.UserResponseDto;
import com.techv.vitor.entity.User;
import com.techv.vitor.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> findAll() {
        var clients = userService.findAll();
        return ResponseEntity.ok().body(clients);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> findById(@PathVariable UUID id) {
        var clients = userService.findById(id);
        return ResponseEntity.ok().body(clients);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponseDto> insertUsers(@RequestBody UserRequestDto requestDto) {
        UserResponseDto response = userService.insertUsers(requestDto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri()).body(response);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponseDto> updateUsers(@RequestBody User user, @PathVariable UUID id) {
        UserResponseDto response = userService.updateUsers(user, id);
        return ResponseEntity.ok().body(response);
    }
}
