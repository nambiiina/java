package com.example.catalogservice.conf;

public final class SecurityParams {
    static final String JWT_HEADER_NAME = "Authorization";
    static final String SECRET = "rthierrynambinina@gmail.com";
    static final Long EXPIRATION = 10L*24*3600*1000;
    static final String TOKEN_PREFIX = "Bearer ";
}
