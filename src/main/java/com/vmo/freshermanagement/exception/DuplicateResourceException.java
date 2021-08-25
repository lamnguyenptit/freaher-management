package com.vmo.freshermanagement.exception;

public class DuplicateResourceException extends RuntimeException{
    public static final long serialVersionUID = 2L;

    public DuplicateResourceException(String message) {
        super(message);
    }
}
