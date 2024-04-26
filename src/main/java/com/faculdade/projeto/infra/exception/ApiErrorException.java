package com.faculdade.projeto.infra.exception;

public abstract class ApiErrorException extends RuntimeException {

    public abstract int getCode();
    public abstract String getMessage();

}
