package com.example.demo.exception;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException() {
        super(ErrorMessages.ACCESS_DENIED);
    }
}
