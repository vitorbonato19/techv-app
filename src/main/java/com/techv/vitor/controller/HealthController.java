package com.techv.vitor.controller;

import com.techv.vitor.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    private UserService userService;

    public HealthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<String> healthCheckApplication() {
        var ok = userService.findAllHealthCheck();
        return ResponseEntity.status(200).body("Application ok!");
    }
}
