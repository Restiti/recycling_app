package com.fisa.recycling_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 🔹 Désactive CSRF (sinon les requêtes POST/PUT peuvent être bloquées)
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // 🔹 Active CORS
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // 🔹 Autorise toutes les requêtes

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // 🔹 Autorise toutes les origines
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 🔹 Autorise toutes les méthodes
        configuration.setAllowedHeaders(List.of("*")); // 🔹 Autorise tous les headers
        configuration.setAllowCredentials(false); // 🔹 Pas de cookies/session
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
