package com.app.backend.util;

import com.app.backend.persistence.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtUtil {

  @Value("${jwt.secret}")
  private String JWT_SECRET;

  @Value("${jwt.expiration}")
  private Long JWT_EXPIRATION;

  public String generateToken(User user, Map<String, Object> claims) {
    Date issuedAt = new Date(System.currentTimeMillis());
    Date expiration = new Date(System.currentTimeMillis() + JWT_EXPIRATION);
    return Jwts.builder().header().type("JWT").and().subject(user.getUsername()).claims(claims)
        .issuedAt(issuedAt).expiration(expiration).signWith(generateKey()).compact();
  }

  private Key generateKey() {
    byte[] secretBytes = Decoders.BASE64.decode(JWT_SECRET);
    return Keys.hmacShaKeyFor(secretBytes);
  }

  private SecretKey getSecretKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
  }

  private Claims extractClaims(String token) {
    return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
  }

  public String extractUsername(String token) {
    return extractClaims(token).getSubject();
  }
}