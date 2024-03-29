package org.sid.customerservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CustommerController {

    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Map<String, String> customer(Authentication authentication) {
        return Map.of(
                "name", "Harti",
                "email", "ha@gmail.com",
                "username", authentication.getName(),
                "scope", authentication.getAuthorities().toString());
    }

}
