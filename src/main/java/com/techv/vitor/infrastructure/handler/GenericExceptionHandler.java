package com.techv.vitor.infrastructure.handler;

import com.techv.vitor.infrastructure.exception.GenericException;
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
public class GenericExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<Map<String, Object>> genericException(GenericException ex, HttpServletRequest request) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", ex.getHttpStatus().value());
        response.put("statusError", ex.getHttpStatus().getReasonPhrase());
        response.put("error", ex.getMessage());
        response.put("cause", ex.getCause());
        response.put("path", request.getRequestURI());

        return new ResponseEntity<>(response, ex.getHttpStatus());
    }
}
