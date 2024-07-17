package com.example.login_auth_api.exceptions;
// Classe que trata a  exceção de user existente
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
