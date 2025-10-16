package com.example.web_jee_spring_mvc_security.exception;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(String username) {
        super("Username " + username + " already exists");
    }
}
