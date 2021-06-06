package com.yolotech.defapi.services;

import com.yolotech.defapi.domain.Account;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {

  private static final long expirationTime = 1800000;
  private String secret = "I am a secret passcode";
  SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

  public String generateToken(Account account) {

    return Jwts.builder()
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setSubject(account.getFullName())
        .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
        .signWith(key)
        .compact();
  }
}
