package com.techv.vitor.controller;

import com.techv.vitor.entity.User;
import com.techv.vitor.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        var clients = userService.findAll();
        return ResponseEntity.ok().body(clients);
    }

    @GetMapping
    public ResponseEntity<Optional<User>> findById(@PathVariable Long id) {
        var clients = userService.findById(id);
        return ResponseEntity.ok().body(clients);
    }

    @PostMapping
    public ResponseEntity<User> insertUsers(@RequestBody UserDTO userDto) {
        
    }
}
