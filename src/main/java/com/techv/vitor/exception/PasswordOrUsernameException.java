package com.techv.vitor.exception;

import org.springframework.http.HttpStatus;

public class PasswordOrUsernameException extends RuntimeException {

    private HttpStatus httpStatus;

    public PasswordOrUsernameException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
