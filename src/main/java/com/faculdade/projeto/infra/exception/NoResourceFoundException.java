package com.faculdade.projeto.infra.exception;

import org.springframework.http.HttpStatus;

public class NoResourceFoundException extends ApiErrorException {
	private static final long serialVersionUID = 1L;
	private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;
    
	private String message;
    public NoResourceFoundException(String message) {
        this.message = message;
    }

    public NoResourceFoundException() {
        super();
        this.message = HTTP_STATUS.getReasonPhrase();
    }

    public int getCode() {
        return HTTP_STATUS.value();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
