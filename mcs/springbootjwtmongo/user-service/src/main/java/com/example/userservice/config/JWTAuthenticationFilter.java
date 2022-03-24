package com.example.userservice.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.userservice.entities.UserTryingToAuthenticate;
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
        try {
            // Get username and password from request
            UserTryingToAuthenticate userTryingToAuthenticate = new ObjectMapper().readValue(request.getInputStream(), UserTryingToAuthenticate.class);
            // Try to authenticate user using custom UserDetailsService and BCryptPasswordEncoder
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userTryingToAuthenticate.getUsername(), userTryingToAuthenticate.getPassword()));
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
        // Get user authenticated
        User authenticatedUser = (User) authResult.getPrincipal();
        List<String> roles = new ArrayList<>();
        authenticatedUser.getAuthorities().forEach(grantedAuthority -> roles.add(grantedAuthority.getAuthority()));
        // Sign and generate jwt
        String jwt = JWT.create()
                .withIssuer(request.getRequestURI())
                .withSubject(authenticatedUser.getUsername())
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .withExpiresAt(new Date(System.currentTimeMillis()+SecurityParams.EXPIRATION))
                .sign(Algorithm.HMAC256(SecurityParams.SECRET));
        //Add Authorization header with content jwt
        response.addHeader(SecurityParams.JWT_HEADER_NAME, SecurityParams.TOKEN_PREFIX + jwt);
    }
}
