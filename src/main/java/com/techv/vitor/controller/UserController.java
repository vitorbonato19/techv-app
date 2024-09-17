package com.techv.vitor.controller;

import com.techv.vitor.controller.dto.LoginRequest;
import com.techv.vitor.controller.dto.LoginResponse;
import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.controller.dto.UserResponseDto;
import com.techv.vitor.entity.Cep;
import com.techv.vitor.entity.Data;
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
    public ResponseEntity<Data<List<User>>> findAll() {
        var clients = userService.findAll();
        var response = new Data<>(clients);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<User>> findById(@PathVariable Long id) {
        var clients = userService.findById(id);
        var response = new Data<>(clients);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<LoginResponse>> userLogin(@RequestBody LoginRequest loginRequest) {
        var login = userService.login(loginRequest);
        var response = new Data<>(login);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Data<UserResponseDto>> insertUsers(@RequestBody UserRequestDto requestDto) {
        UserResponseDto dtoResponse = userService.insertUsers(requestDto);
        var response = new Data<>(dtoResponse);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dtoResponse.getId())
                .toUri()).body(response);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<UserResponseDto>> updateUsers(@RequestBody User user, @PathVariable Long id) {
        UserResponseDto dtoResponse = userService.updateUsers(user, id);
        var response = new Data<>(dtoResponse);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseEntity<Void>> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
