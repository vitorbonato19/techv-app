package com.techv.vitor.handler;

import com.techv.vitor.exception.InvalidRequestException;
import com.techv.vitor.exception.TicketNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class InvalidRequestExceptionHandler {

    @ResponseBody
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Map<String, Object>> entityNotFound(InvalidRequestException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", ex.getHttpStatus().value());
        response.put("statusError", ex.getHttpStatus().getReasonPhrase());
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, ex.getHttpStatus());
    }
}
