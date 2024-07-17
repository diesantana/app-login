package com.example.login_auth_api.exceptions;
// Classe que trata exceções de geração de token
public class TokenGenerationException extends RuntimeException {
    public TokenGenerationException(String message) {
        super(message);
    }    
    public TokenGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
