package com.techv.vitor.exception;

import org.springframework.http.HttpStatus;

public class TicketCreatedException extends RuntimeException {

    private HttpStatus httpStatus;

    public TicketCreatedException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
