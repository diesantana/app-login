package com.example.login_auth_api.services;

import com.example.login_auth_api.domain.User;
import com.example.login_auth_api.dtos.UserDTO;
import com.example.login_auth_api.repositories.UserRepository;
import com.example.login_auth_api.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Transactional(readOnly = true)
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return convertEntityToDTO(user);
    }

    // Converte entidade para DTO
    private UserDTO convertEntityToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
