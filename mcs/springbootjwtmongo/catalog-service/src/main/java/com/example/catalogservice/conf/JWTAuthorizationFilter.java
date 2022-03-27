package com.example.catalogservice.conf;

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
        // CORS
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-Width, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, Authorization");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            // Get Authorization
            String jwt = request.getHeader(SecurityParams.JWT_HEADER_NAME);
            // Verify if authorization is presents in header request
            if (jwt == null || !jwt.startsWith(SecurityParams.TOKEN_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }
            //Get token
            String token = jwt.substring(SecurityParams.TOKEN_PREFIX.length());
//            System.out.println(token);
            //Create token verifier
            Algorithm algorithm = Algorithm.HMAC256(SecurityParams.SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
//                .withIssuer("oauth")
                    .build();
            //Verify token
            DecodedJWT verify = verifier.verify(token);
            //Decode token
            DecodedJWT decodedJWT = JWT.decode(token);
            String username = decodedJWT.getSubject();
//            System.out.println(username);
            List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
//            System.out.println(roles);
            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            //Authenticate the user
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        }
    }
}
