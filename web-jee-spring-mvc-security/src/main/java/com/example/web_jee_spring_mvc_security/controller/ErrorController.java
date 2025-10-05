package com.example.web_jee_spring_mvc_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/403")
    public String notAuthorized() {
        return "403";
    }
}
