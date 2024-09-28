package com.parcial.backend.security.jwt;

//import java.security.Key;
//import java.security.Signature;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.crypto.SecretKey;

//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import com.parcial.backend.model.entity.Users;

@Service
public class JwtService {

    private static final String SECRET_KEY="1238NKDSFKURY48CF47CFHN4LIR39R84O47753987N18KEWNYT387SJY3484DJJKSFJH";

    public String getToken(Users user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("name",user.getName());
        claims.put("email",user.getEmail());
        claims.put("role",user.getRole());
        claims.put("profilePic",user.getProfilePic());


       return getToken(claims, user);
    }

    private String getToken(Map<String, Object> extraClaims, Users user) {
        return Jwts
            .builder()
            .claims(extraClaims)
            .subject(user.getEmail())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis()+10000*60*24))
            .signWith(getKey())
            .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, Optional<Users> userDet){
        final String username = getUsernameFromToken(token);
        return userDet.map(users -> username.equals(users.getEmail()) && !isTokenExpired(token)).orElse(false);
        
    }

    private Claims getAllClaims(String token){
        return Jwts 
            .parser()
            .verifyWith(getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token){
        return getClaim(token,Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }

}
