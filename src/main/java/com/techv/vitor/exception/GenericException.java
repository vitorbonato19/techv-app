package com.techv.vitor.exception;

import org.springframework.http.HttpStatus;

public class GenericException extends RuntimeException {

    private final HttpStatus httpStatus;

    public GenericException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
