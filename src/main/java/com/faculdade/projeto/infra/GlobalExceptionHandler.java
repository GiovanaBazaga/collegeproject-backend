package com.faculdade.projeto.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.faculdade.projeto.infra.exception.ApiErrorException;
import com.faculdade.projeto.infra.exception.ForbiddenException;
import com.faculdade.projeto.infra.exception.UnauthorizedException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<ResponseError> handleApiError(final ApiErrorException exception) {

        return ResponseEntity
            .status(exception.getCode())
            .body(new ResponseError(exception.getMessage(), exception.getCode()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseError> handleAuthenticationException(final AuthenticationException exception) {
        final var unauthorizedException = new UnauthorizedException();

        return ResponseEntity
            .status(unauthorizedException.getCode())
            .body(new ResponseError(unauthorizedException.getMessage(), unauthorizedException.getCode()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ResponseError> handleAccessDeniedException(final ForbiddenException forbiddenException) {

        return ResponseEntity
            .status(forbiddenException.getCode())
            .body(new ResponseError(forbiddenException.getMessage(), forbiddenException.getCode()));
    }


}