package com.example.login_auth_api.exceptions;
// Classe de exceção personalizada para recursos não encontrados
public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
