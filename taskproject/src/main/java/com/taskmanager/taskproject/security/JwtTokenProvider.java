package com.taskmanager.taskproject.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.taskmanager.taskproject.exception.ApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;

    public JwtTokenProvider() {
        // Generate a secure key for HS512 algorithm
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateToken(Authentication authentication) {
        String email = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + 3600000); // 1 hour expiration

        // Generate JWT token
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(secretKey, SignatureAlgorithm.HS512) // Specify the algorithm and secure key
                .compact();
    }

    public String getEmailFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey) // Set the signing key for the parser
                    .build()
                    .parseClaimsJws(token) // Parse the JWT token
                    .getBody();
            return claims.getSubject(); // Extract the email (subject) from the claims
        } catch (Exception e) {
            throw new ApiException("Invalid token: " + e.getMessage());
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey) // Set the signing key for the parser
                .build()
                .parseClaimsJws(token); // Parse the JWT token
            return true; // If no exception, token is valid
        } catch (Exception e) {
            throw new ApiException("Token validation failed: " + e.getMessage());
        }
    }
}
