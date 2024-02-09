package org.sid.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notAuthorized")
public class SecurityController {

    @GetMapping
    public String notAuthorized() {
        return "Not authorized";
    }
}
