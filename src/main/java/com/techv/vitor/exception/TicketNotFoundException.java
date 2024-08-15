package com.techv.vitor.exception;

import org.springframework.http.HttpStatus;

public class TicketNotFoundException extends RuntimeException {

    private HttpStatus httpStatus;

    public TicketNotFoundException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
