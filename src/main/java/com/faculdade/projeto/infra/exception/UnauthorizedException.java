package com.faculdade.projeto.infra.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApiErrorException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;

    private String message;

    public UnauthorizedException() {
        super();
        this.message = HTTP_STATUS.getReasonPhrase();
    }

    public UnauthorizedException(String message) {
        this.message = message;
    }

    public int getCode() {
        return HTTP_STATUS.value();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
