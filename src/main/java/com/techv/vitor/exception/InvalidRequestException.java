package com.techv.vitor.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends RuntimeException {

    private final HttpStatus httpStatus;

    public InvalidRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
