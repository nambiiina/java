package org.sid.springsecurity.common;

public class JWTUtil {
    public static final String SECRET = "mySecret";
    public static final String AUTH_HEADER = "Authorization";
    public static final String PREFIXE = "Bearer ";
    public static final long EXPIRE_ACCESS_TOKEN = 2*60*1000;
    public static final long EXPIRE_REFRESH_TOKEN = 15*60*1000;
}
