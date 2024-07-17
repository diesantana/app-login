package com.example.login_auth_api.services;

import com.example.login_auth_api.domain.User;
import com.example.login_auth_api.dtos.LoginRequestDTO;
import com.example.login_auth_api.dtos.RegisterRequestDTO;
import com.example.login_auth_api.dtos.ResponseDTO;
import com.example.login_auth_api.dtos.UserDTO;
import com.example.login_auth_api.exceptions.InvalidPasswordException;
import com.example.login_auth_api.infra.security.TokenService;
import com.example.login_auth_api.repositories.UserRepository;
import com.example.login_auth_api.exceptions.ResourceNotFoundException;
import com.example.login_auth_api.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    
    // Busca por email
    @Transactional(readOnly = true)
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return convertEntityToDTO(user);
    }
    
    // Lógica de login
    public ResponseDTO login(LoginRequestDTO loginRequestDTO) {
        // Buscar o user no banco de dados
        User user = userRepository.findByEmail(loginRequestDTO.email())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Verifica se a senha bate com a senha codificada na nosssa db
        if(!passwordEncoder.matches(loginRequestDTO.password(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }
        
        // Gera o token
        String token = this.tokenService.generateToken(user);
        return new ResponseDTO(user.getName(), token);
    }
    
    // Lógica de registro
    public ResponseDTO register(RegisterRequestDTO dto) {
        Optional<User> user = this.userRepository.findByEmail(dto.email());
        // se o user estiver presente lança a exception
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + dto.email() + " already exists.");
        }
        // Cria o user se não houver 
        User newUser = new User();
        newUser.setName(dto.name());
        newUser.setEmail(dto.email());
        newUser.setPassword(passwordEncoder.encode(dto.password()));
        
        // Salva o user na base de dados
        newUser = this.userRepository.save(newUser);
        // gera o token
        String token = tokenService.generateToken(newUser);
        return new ResponseDTO(newUser.getName(), token);
    }

    // Converte entidade para DTO
    private UserDTO convertEntityToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
