package com.example.userservice.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.userservice.entities.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        /**
         * Get username and password of user to try to authenticate
         */
        try {
            AppUser UserTryingToAuthenticate = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(UserTryingToAuthenticate.getUsername(), UserTryingToAuthenticate.getPassword()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        /**
         * Generate jwt token
         * jwt :
         *  - header
         *  - payload
         *  - signature
         */
        User authenticatedUser = (User) authResult.getPrincipal();
        List<String> roles = new ArrayList<>();
        authenticatedUser.getAuthorities().forEach(grantedAuthority -> roles.add(grantedAuthority.getAuthority()));
        String jwt = JWT.create()
                .withIssuer(request.getRequestURI())
                .withSubject(authenticatedUser.getUsername())
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .withExpiresAt(new Date(System.currentTimeMillis()+10*24*3600))
                .sign(Algorithm.HMAC256("rthierrynambinina@gmail.com"));
        response.addHeader("Authorization", "Bearer " + jwt);
    }
}
