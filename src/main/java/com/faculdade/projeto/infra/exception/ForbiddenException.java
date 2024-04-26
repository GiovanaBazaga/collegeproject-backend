package com.faculdade.projeto.infra.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends ApiErrorException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.FORBIDDEN;

    private String message;

    public ForbiddenException() {
        super();
        this.message = HTTP_STATUS.getReasonPhrase();
    }

    public ForbiddenException(String message) {
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
