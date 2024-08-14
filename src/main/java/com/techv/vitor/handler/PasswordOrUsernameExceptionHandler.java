package com.techv.vitor.handler;

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
public class PasswordOrUsernameExceptionHandler {

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    @ExceptionHandler(PasswordOrUsernameException.class)
    public ResponseEntity<Map<String, Object>> handler(PasswordOrUsernameException ex, HttpServletRequest request) {

        Map<String, Object> handlerResponse = new HashMap<>();
        handlerResponse.put("Status", ex.getHttpStatus().value());
        handlerResponse.put("Error", ex.getHttpStatus().getReasonPhrase());
        handlerResponse.put("Caused by", ex.getMessage());
        handlerResponse.put("Path", request.getRequestURI());

        return new ResponseEntity<>(handlerResponse, ex.getHttpStatus());
    }
}