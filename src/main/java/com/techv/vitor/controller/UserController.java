package com.techv.vitor.controller;

import com.techv.vitor.controller.dto.LoginRequest;
import com.techv.vitor.controller.dto.LoginResponse;
import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.controller.dto.UserResponseDto;
import com.techv.vitor.entity.Cep;
import com.techv.vitor.entity.User;
import com.techv.vitor.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

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
    public ResponseEntity<User> findById(@PathVariable Long id) {
        var clients = userService.findById(id);
        return ResponseEntity.ok().body(clients);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        var response = userService.login(loginRequest);
        return ResponseEntity.ok().body(response);
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
    public ResponseEntity<UserResponseDto> updateUsers(@RequestBody User user, @PathVariable Long id) {
        UserResponseDto response = userService.updateUsers(user, id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseEntity<Void>> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
