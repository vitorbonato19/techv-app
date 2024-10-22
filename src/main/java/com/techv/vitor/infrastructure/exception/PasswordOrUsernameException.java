package com.techv.vitor.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class PasswordOrUsernameException extends RuntimeException {

    private final HttpStatus httpStatus;

    public PasswordOrUsernameException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
