package com.example.web_jee_spring_mvc_security.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String role) {
        super("Role " + role + " not found");
    }
}
