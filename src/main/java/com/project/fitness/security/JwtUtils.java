package com.project.fitness.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;

//helper class
@Component
public class JwtUtils {
    private String jwtSecret="9a4f2c5b3e1d7a8b6c4d2e0f1a3b5c7d8e9f0a1b2c3d4e5f6a7b8c9d0e1f2a3b";
    private int jwtExpirationMs=86400000;

    public String getJwtFromHeader(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken !=null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        else {
            return null;
        }
    }

    //TOKEN GENERATION
    public String generateToken(String userId,String role){

        return Jwts.builder()
                .subject
                        (userId)
                .claim("roles", List.of(role))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime()+jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    public boolean validateJwtToken(String jwtToken){
        try {
            Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwtToken);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserIdFromToken(String jwt) {
        return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwt).getPayload().getSubject();
    }

    public Claims getAllClaims(String jwt) {
        return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwt).getPayload();
    }
}
