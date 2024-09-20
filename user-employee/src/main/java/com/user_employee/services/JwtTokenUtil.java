package com.user_employee.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import java.util.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtTokenUtil {

    // Static secret key for signing the JWT
    private static Key SECRET_KEY;

    // Static block to initialize the secret key using HMAC-SHA algorithm
    static {
        SECRET_KEY = generateHmacShaKey(); // Generate key using HMAC-SHA algorithm
        if (SECRET_KEY == null) {
            throw new IllegalArgumentException("Key generation failed. Key cannot be null.");
        }
    }

    // Method to generate a key using HMAC-SHA algorithm
    private static Key generateHmacShaKey() {
        try {
            // Using HMAC-SHA256 (or you can use "HmacSHA512" for SHA-512)
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            keyGenerator.init(256); // 256-bit key for HMAC-SHA256
            SecretKey secretKey = keyGenerator.generateKey();

            // Encode the key as a Base64 string for storage or transmission
            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

            // Convert the Base64 encoded key to a Key object
            return new SecretKeySpec(Base64.getDecoder().decode(encodedKey), "HmacSHA256");
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate HMAC-SHA key", e);
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
    // Static method to generate the JWT token
    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    // Static helper method to create the token
    private static String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) // Sign with the custom HMAC-SHA key
                .compact();
    }

    // Static method to retrieve the secret key if needed
    public static Key getSigningKey() {
        return SECRET_KEY;
    }
}
