package com.customer.customermanagerbackend.common.exception.errorresponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ApiExceptionHandler extends DefaultResponseErrorHandler {

    @ExceptionHandler(ApiNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse apiNotFoundExceptionHandler(ApiNotFoundException ex) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .field(NOT_FOUND.name())
                .parameter(ex.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse dataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .field(BAD_REQUEST.name())
                .parameter(ex.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage())
                .field(BAD_REQUEST.name())
                .parameter(ex.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse webExchangeBindExceptionHandler(WebExchangeBindException ex) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage())
                .field(BAD_REQUEST.name())
                .parameter(ex.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse runtimeExceptionHandler(Exception ex) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .field(INTERNAL_SERVER_ERROR.name())
                .parameter(ex.getClass().getSimpleName())
                .build();
    }
}
