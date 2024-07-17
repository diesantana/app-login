package com.example.login_auth_api.exceptions;
// Classe de exceção personalizada para recursos não encontrados
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
