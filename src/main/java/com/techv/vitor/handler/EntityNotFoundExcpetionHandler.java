package com.techv.vitor.handler;

import com.techv.vitor.exception.EntityNotFoundException;
import com.techv.vitor.exception.PasswordOrUsernameException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EntityNotFoundExcpetionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> entityNotFound(EntityNotFoundException ex, HttpServletRequest request) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", ex.getHttpStatus());
        response.put("error", ex.getMessage());
        response.put("cause", ex.getCause());
        response.put("path", request.getRequestURI());

        return new ResponseEntity<>(response, ex.getHttpStatus());
    }
}