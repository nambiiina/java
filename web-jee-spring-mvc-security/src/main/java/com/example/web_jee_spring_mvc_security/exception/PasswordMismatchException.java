package com.example.web_jee_spring_mvc_security.exception;

public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException() {
        super("Password and confirmation do not match");
    }
}
