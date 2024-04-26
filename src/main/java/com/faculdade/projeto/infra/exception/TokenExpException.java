package com.faculdade.projeto.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenExpException extends ApiErrorException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;

    private String message;

    public TokenExpException() {
        super();
        this.message = HTTP_STATUS.getReasonPhrase();
    }

    public TokenExpException(String message) {
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
