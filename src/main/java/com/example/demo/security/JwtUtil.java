package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.github.cdimascio.dotenv.Dotenv;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;
    private final long EXPIRATION;

public JwtUtil() {

        // Загружаем .env
        Dotenv dotenv = Dotenv.load();

        String secret = dotenv.get("JWT_SECRET");
        String expirationEnv = dotenv.get("JWT_EXPIRATION");

        if (secret == null || secret.length() < 32) {
            throw new IllegalStateException("JWT_SECRET must be at least 32 chars long");
        }

        this.key = Keys.hmacShaKeyFor(secret.getBytes());

        this.EXPIRATION = (expirationEnv != null)
                ? Long.parseLong(expirationEnv)
                : 3600000L; 
    }

    // Создание токена
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        return getClaims(token).getExpiration().after(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
