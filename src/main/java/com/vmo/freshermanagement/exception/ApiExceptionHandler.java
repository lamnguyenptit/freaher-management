package com.vmo.freshermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleAllException(Exception exception, WebRequest webRequest){
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getLocalizedMessage(), webRequest.getDescription(false));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleResourceNotFoundException(Exception exception, WebRequest webRequest){
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), exception.getMessage(), webRequest.getDescription(false));
    }

    @ExceptionHandler(DuplicateResourceException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage handleDuplicateEmailException(Exception exception, WebRequest webRequest){
        return new ErrorMessage(HttpStatus.CONFLICT.value(), exception.getMessage(), webRequest.getDescription(false));
    }
}
