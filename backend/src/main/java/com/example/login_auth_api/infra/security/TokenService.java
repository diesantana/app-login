package com.example.login_auth_api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.login_auth_api.domain.User;
import com.example.login_auth_api.exceptions.TokenGenerationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

// Service responsável por gerar e validar o token
@Service
public class TokenService {
    
    @Value("${api.security.token.secret}") // pega p valor secret do application.properties
    private String secret;
    
    // Gera o token
    public String generateToken(User user) {
        try {
            // Algortimo de geração do token
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // Gerando o token
            String token = JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new TokenGenerationException("Error while generating token", e);
        }
    }

    // Valida o token
    public String validateToken(String token) {
        try {
            // Algortimo de geração do token
            Algorithm algorithm = Algorithm.HMAC256(secret);
            
            // Valida o token
            return JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token)
                    .getSubject(); // retorna o Subject (Email do user)
        } catch (JWTVerificationException e) {
            // Retorna nulo se o token for inválido
            return null;
        }
    }
    
    // Define a duração do token
    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
