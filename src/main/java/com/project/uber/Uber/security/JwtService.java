package com.project.uber.Uber.security;

import com.project.uber.Uber.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    private SecretKey getSecretKey() {
        if (secretKey == null || secretKey.length() < 32) {
            throw new IllegalArgumentException("Secret key must be at least 32 characters long for HS256:"+secretKey.length()+secretKey);
        }
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String getAccessJwtToken(User user){
        return Jwts
                .builder()
                .subject(String.valueOf(user.getId()))
                .claim("email",user.getEmail())
                .claim("role",user.getRoles().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(getSecretKey())
                .compact();
    }
    public String getRefreshJwtToken(User user){
        return Jwts
                .builder()
                .subject(String.valueOf(user.getId()))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ 1000L *60*60*24*30*6))
                .signWith(getSecretKey())
                .compact();
    }

    public Long getUserId(String token){
        Claims claims = Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.valueOf(claims.getSubject());
    }
}
