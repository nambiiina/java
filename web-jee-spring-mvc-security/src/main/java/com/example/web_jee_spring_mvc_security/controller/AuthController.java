package com.example.web_jee_spring_mvc_security.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final JdbcTokenRepositoryImpl jdbcTokenRepository;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null)  jdbcTokenRepository.removeUserTokens(authentication.getName());
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        return "redirect:/login";
    }
}
