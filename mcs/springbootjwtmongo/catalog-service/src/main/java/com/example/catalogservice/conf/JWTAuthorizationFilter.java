package com.example.catalogservice.conf;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("***********************");

        String jwt = request.getHeader("Authorization");
        if (jwt == null) throw new RuntimeException("Not Authorized !");
        filterChain.doFilter(request, response);
    }
}
