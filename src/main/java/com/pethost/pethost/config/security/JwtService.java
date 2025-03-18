package com.pethost.pethost.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    // ✅ Gera um token JWT
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", userDetails.getUsername()); // 🔥 Garante que o token contém o UID
        return buildToken(claims, userDetails, jwtExpiration);
    }

    // ✅ Constrói o token com claims personalizadas
    private String buildToken(Map<String, Object> claims, UserDetails userDetails, long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername()) // 🔥 O UID será o subject
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Extrai o UID do token
    public String extractUid(String token) {
        return extractAllClaims(token).getSubject(); // 🔥 Agora retorna o UID diretamente
    }

    // ✅ Verifica se o token é válido
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String uid = extractUid(token);
        return (uid.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // ✅ Verifica se o token expirou
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // ✅ Extrai a data de expiração do token
    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    // ✅ Retorna as claims do token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ Obtém a chave de assinatura
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
