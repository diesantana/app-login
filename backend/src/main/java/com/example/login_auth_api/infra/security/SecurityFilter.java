package com.example.login_auth_api.infra.security;


import com.example.login_auth_api.domain.User;
import com.example.login_auth_api.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

//Cadeia de filtro de segurança
// vai pegar o retorno do token e persistir os dados do user válido

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;
    

    // Filtro de segurança interno
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, 
            HttpServletResponse response, 
            FilterChain filterChain
    ) throws ServletException, IOException {
        
        var token = this.recoverToken(request); // recupera o token
        var authenticatedEmail = tokenService.validateToken(token); // faz a validação
        // Lógica em caso de token válido
        if(authenticatedEmail != null) {
            // Busca o usuário no banco de dados
            User user = userRepository.findByEmail(authenticatedEmail)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            // cria uma lista imutável contendo uma única autoridade (SimpleGrantedAuthority) com o papel "ROLE_USER"
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            // armazena os dados de autenticação (user, e authorities)
            // * as credentials (null) ñ são necessários no filtro
            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            // persistindo as informações de autenticação do usuário no contexto de segurança do Spring Security
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        // permite que a requisição HTTP atual continue seu processamento normal
        filterChain.doFilter(request, response);
    }
    
    // Método Aux. para recuperar o token
    private String recoverToken(HttpServletRequest request) {
        // Pega o token que vem no header Authorization
        var authHeader = request.getHeader("Authorization");
        // Se não houver token retorna null
        if(authHeader == null) return null;
        // Retorna o token removendo a string "Bearer" e espaços em branco
        return authHeader.replace("Bearer ", "");
    }
    
}
