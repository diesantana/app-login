package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.User;
import com.example.login_auth_api.dtos.LoginRequestDTO;
import com.example.login_auth_api.dtos.RegisterRequestDTO;
import com.example.login_auth_api.dtos.ResponseDTO;
import com.example.login_auth_api.infra.security.TokenService;
import com.example.login_auth_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
        // Buscar o user no banco de dados
        User user = userRepository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Verifica se a senha bate com a senha codificada na nosssa db
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            // Gera o token
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        // **********************************************************************************
        // Modificar essa parte posteriormente para tratamento de exceções no seu devido lugar
        return ResponseEntity.badRequest().build();
    }
    
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) {
        // verifica se o user existe
        Optional<User> user = this.userRepository.findByEmail(body.email());
        if(user.isEmpty()) {
            // Registra o novo user
            User newUser = new User();
            newUser.setName(body.name());
            newUser.setEmail(body.email());
            newUser.setPassword(passwordEncoder.encode(body.password()));
            this.userRepository.save(newUser);
            
            // Gera o token
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        // **********************************************************************************
        // Modificar essa parte posteriormente para tratamento de exceções no seu devido lugar
        return ResponseEntity.badRequest().build();
    }
}
