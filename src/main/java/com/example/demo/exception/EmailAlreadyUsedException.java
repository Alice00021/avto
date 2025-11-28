package com.example.demo.exception;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException() {
        super(ErrorMessages.EMAIL_ALREADY_USED);
    }
}
