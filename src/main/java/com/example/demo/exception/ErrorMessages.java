package com.example.demo.exception;

public final class ErrorMessages {
    private ErrorMessages() {
        
    }
    public static final String ACCESS_DENIED = "Недостаточно прав: только админ может создавать пользователей";
    public static final String EMAIL_ALREADY_USED = "Пользователь с таким email уже существует";
}
