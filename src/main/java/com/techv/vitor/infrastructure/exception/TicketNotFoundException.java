package com.techv.vitor.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class TicketNotFoundException extends RuntimeException {

    private final HttpStatus httpStatus;

    public TicketNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
