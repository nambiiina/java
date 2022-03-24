package com.example.userservice.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get Authorization
        String auth = request.getHeader(SecurityParams.JWT_HEADER_NAME);
        // Verify if authorization is presents in header request
        if (auth == null || !auth.startsWith(SecurityParams.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        //Get token
        String token = auth.substring(SecurityParams.TOKEN_PREFIX.length());
        //Create token verifier
        Algorithm algorithm = Algorithm.HMAC256(SecurityParams.SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
//                .withIssuer("auth0")
                .build();
        //Verify token
        DecodedJWT verify = verifier.verify(token);
        //Decode token
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
        System.out.println(roles);
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, authorities);
        //Authenticate the user
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
