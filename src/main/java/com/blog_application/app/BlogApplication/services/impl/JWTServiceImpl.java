package com.blog_application.app.BlogApplication.services.impl;

import com.blog_application.app.BlogApplication.services.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class JWTServiceImpl implements JWTService {
    private static final Logger logger = Logger.getLogger(JWTServiceImpl.class.getName());
    private static final long TOKEN_EXPIRATION_MILLIS = 60 * 60 * 1000; // 1 hour
    private final String secretKey;

    public JWTServiceImpl() {
        this.secretKey = generateSecretKey();
    }

    private String generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGenerator.generateKey();
            return Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, "Error generating secret key", e);
            throw new RuntimeException("Error generating secret key", e);
        }
    }

    @Override
    public String generateToken(String username) {
        try{
            Map<String, Object> claims = new HashMap<>();
            return Jwts.builder()
                    .claims()
                    .add(claims)
                    .subject(username)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
                    .and()
                    .signWith(getSigningKey())
                    .compact();
        }catch (JwtException e) {
            logger.log(Level.SEVERE, "Error generating token for username: " + username, e);
            throw new RuntimeException("Failed to generate token", e);
        }
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String extractUsernameFromToken(String token) {
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (JwtException e) {
            logger.log(Level.SEVERE, "Error extracting username from token", e);
            throw new RuntimeException("Invalid token format or signature", e);
        }
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
       try{
           return Jwts.parser()
                   .verifyWith(getSigningKey())
                   .build().
                   parseSignedClaims(token)
                   .getPayload();
       }catch (JwtException e) {
           logger.log(Level.SEVERE, "Error extracting claims from token", e);
           throw new RuntimeException("Failed to parse token", e);
       }
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
      try{
          final String username = extractUsernameFromToken(token);
          return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
      }catch (JwtException e) {
          logger.log(Level.SEVERE, "Error validating token for user: " + userDetails.getUsername(), e);
          return false;
      }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
