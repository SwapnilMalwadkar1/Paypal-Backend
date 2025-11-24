package com.paypal.User_service.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import java.security.Key;
import java.util.Date;
import java.util.Map;


@Component
public class JWTUtil {

    private static final String SECERT = "my_super_long_secret_key_that_is_atleast_32_bytes";


    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(SECERT.getBytes());
    }

    public String extractEmail(String token){
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken (String token , String username){
        try{
            extractEmail(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public String extractUsername(String token){
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getBody()
                .getSubject();
    }

    public String generateToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractRole(String token) {
        return (String) Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }

}
