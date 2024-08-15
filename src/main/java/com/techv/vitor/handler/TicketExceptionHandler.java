package com.techv.vitor.handler;

import com.techv.vitor.exception.PasswordOrUsernameException;
import com.techv.vitor.exception.TicketCreatedException;
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
public class TicketExceptionHandler {

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    @ExceptionHandler(TicketCreatedException.class)
    public ResponseEntity<Map<String, Object>> handler(PasswordOrUsernameException ex, HttpServletRequest request) {

        Map<String, Object> handlerResponse = new HashMap<>();
        handlerResponse.put("status", ex.getHttpStatus().value());
        handlerResponse.put("error", ex.getHttpStatus().getReasonPhrase());
        handlerResponse.put("caused by", ex.getMessage());
        handlerResponse.put("path", request.getRequestURI());

        return new ResponseEntity<>(handlerResponse, ex.getHttpStatus());
    }
}
