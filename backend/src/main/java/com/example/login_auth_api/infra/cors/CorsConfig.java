package com.example.login_auth_api.infra.cors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //Permite que qualquer endpoint na aplicação ("/**") aceite requisições de outros domínios
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Especifica o domínio liberado
                .allowedMethods("GET", "POST", "DELETE", "PUT"); // especifica os métodos liberados 
    }
    
}
