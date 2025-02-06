package com.techv.vitor.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techv.vitor.controller.dto.LoginRequest;
import com.techv.vitor.controller.dto.LoginResponse;
import com.techv.vitor.entity.Data;
import com.techv.vitor.service.AuthService;
import com.techv.vitor.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	
	private final HttpHeaders headers;
	
	private final Data data;
	

    public AuthController(AuthService authService, HttpHeaders headers, Data data) {
		this.authService = authService;
		this.headers = headers;
		this.data = data;
	}

	@PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<LoginResponse>> userLogin(@RequestBody LoginRequest loginRequest) {
        var responseLogin = authService.auth(loginRequest);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setDate(Instant.now());
        headers.setAccessControlAllowMethods(List.of(HttpMethod.POST));
        var headerData = data.convertToMap(headers);
        var response = new Data<>(responseLogin, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
