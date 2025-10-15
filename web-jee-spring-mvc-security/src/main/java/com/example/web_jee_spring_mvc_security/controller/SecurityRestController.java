package com.example.web_jee_spring_mvc_security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@PreAuthorize("hasRole('ADMIN')")
public class SecurityRestController {

    @GetMapping("/whoami")
    public Authentication getAuthentication(Authentication authentication) {
        return authentication;
    }

}
