package com.techv.vitor.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class NotAdminException extends RuntimeException {

    private final HttpStatus httpStatus;

    public NotAdminException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
