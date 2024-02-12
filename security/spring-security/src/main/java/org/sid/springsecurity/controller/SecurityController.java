package org.sid.springsecurity.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.sid.springsecurity.common.JWTUtil;
import org.sid.springsecurity.entity.AppRole;
import org.sid.springsecurity.entity.AppUser;
import org.sid.springsecurity.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class SecurityController {

    private AccountService accountService;

    @GetMapping("/authenticatedUser")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/notAuthorized")
    public String notAuthorized() {
        return "Not authorized";
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationToken = request.getHeader(JWTUtil.AUTH_HEADER);
        if (authorizationToken != null && authorizationToken.startsWith(JWTUtil.PREFIXE)) {
            String refreshToken = authorizationToken.substring(JWTUtil.PREFIXE.length());
            Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
            String username = decodedJWT.getSubject();
            AppUser appUser = accountService.loadUserByUsername(username);
            String jwtAccessToken = JWT.create()
                    .withSubject(appUser.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_ACCESS_TOKEN))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", appUser.getAppRoles().stream().map(AppRole::getRoleName).collect(Collectors.toList()))
                    .sign(algorithm);

            String jwtRefreshToken = JWT.create()
                    .withSubject(appUser.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_REFRESH_TOKEN))
                    .withIssuer(request.getRequestURL().toString())
                    .sign(algorithm);

            Map<String, String> idToken = new HashMap<>();
            idToken.put("access-token", jwtAccessToken);
            idToken.put("refresh-token", jwtRefreshToken);
            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getOutputStream(), idToken);
        }
    }
}
