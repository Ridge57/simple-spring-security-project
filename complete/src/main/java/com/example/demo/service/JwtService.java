package com.example.demo.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    private String SECRET_KEY = "lsvksflfovngibvitbgitnibnrinjtirbirbnirobnrtb";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateJwt(String name) {
        return Jwts.builder()
                .subject(name)
                .issuedAt(new Date())
                .expiration(
                        Date.from(LocalDate.now()
                                .plusDays(1)
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant())
                )
                .signWith(getSigningKey())
                .compact();
    }


    public boolean isValid(String token) {
             try {
                 Date exp = Jwts
                         .parser()
                         .verifyWith(getSigningKey())
                         .build()
                         .parseSignedClaims(token)
                         .getPayload()
                         .getExpiration();
                 return exp.after(new Date());
             } catch (JwtException e) {
                 System.out.println(e.getMessage());
                 return false;
             }
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
