package com.huntech.ms.auth_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private final long TIME_SECRET_TOKEN = 5 * 60 * 60 * 1000;
    @Value("${jwt.secret}")
    private String JWT_KEY;

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")));
        claims.put("username", userDetails.getUsername());

        SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes());

        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TIME_SECRET_TOKEN))
                .signWith(key)
                .compact();
    }

    public Claims getAllClaimsFromToken(String token) {
        final SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes());

        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimFunction) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimFunction.apply(claims);
    }

    public String getUsernameFromToken(String token){ return getClaimFromToken(token, Claims::getSubject);}

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
