package com.faculdade.projeto.infra.exception;

public abstract class ApiErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public abstract int getCode();
    public abstract String getMessage();

}
