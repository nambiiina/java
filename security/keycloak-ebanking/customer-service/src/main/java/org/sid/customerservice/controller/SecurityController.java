package org.sid.customerservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/security")
public class SecurityController {


    @GetMapping("/authentication")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }

}
