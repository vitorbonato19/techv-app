package com.techv.vitor.handler;

import com.techv.vitor.exception.NotAdminException;
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
public class NotAdminExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    @ExceptionHandler(NotAdminException.class)
    public ResponseEntity<Map<String, Object>> entityNotFound(NotAdminException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", ex.getHttpStatus().value());
        response.put("statusError", ex.getHttpStatus().getReasonPhrase());
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, ex.getHttpStatus());
    }
}
