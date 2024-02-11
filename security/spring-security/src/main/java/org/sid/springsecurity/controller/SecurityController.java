package org.sid.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/authenticatedUser")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/notAuthorized")
    public String notAuthorized() {
        return "Not authorized";
    }
}
