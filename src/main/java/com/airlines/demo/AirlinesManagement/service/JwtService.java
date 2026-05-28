package com.airlines.demo.AirlinesManagement.service;

import com.airlines.demo.AirlinesManagement.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final String Secret =  "thisismysecretcodetoencode";

    public String generateToken(String username){

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60)
                )
                .signWith(
                        Keys.hmacShaKeyFor(
                                Secret.getBytes()
                        ),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }
    public String extractUsername(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(
                        Secret.getBytes()
                )
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean isTokenValid(
            String token,
            UserDetails user
    ) {

        String username =
                extractUsername(token);

        return username.equals(
                user.getUsername()
        );
    }
}
