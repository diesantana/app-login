package com.example.login_auth_api.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    // Trata exceções do tipo ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setTimestamp(Instant.now());
        errorDTO.setError("Resource not found");
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage(e.getMessage());
        errorDTO.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }
    
    // Trata exceção do tipo InvalidPasswordException
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorDTO> invalidPassword(InvalidPasswordException e, HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setTimestamp(Instant.now());
        errorDTO.setError("Invalid Password");
        errorDTO.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorDTO.setMessage(e.getMessage());
        errorDTO.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDTO);
    }

    // Trata exceção do tipo TokenGenerationException
    @ExceptionHandler(TokenGenerationException.class)
    public ResponseEntity<ErrorDTO> tokenGeneration(TokenGenerationException e, HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setTimestamp(Instant.now());
        errorDTO.setError("Internal server error");
        errorDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDTO.setMessage(e.getMessage());
        errorDTO.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }

    // Trata exceção do tipo UserAlreadyExistsException
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> userAlreadyExists(UserAlreadyExistsException e, HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setTimestamp(Instant.now());
        errorDTO.setError("User Already exists");
        errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDTO.setMessage(e.getMessage());
        errorDTO.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }



}
