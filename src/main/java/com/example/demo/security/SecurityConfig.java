package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity/*  включает поддержку Spring Security. */
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean /* spring сообщает создать объект этого метода и положить его в контейнер как конфигурационный компонент. */
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Отключаем CSRF
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login").permitAll() // разрешаем логин без токена
                .anyRequest().authenticated()               // остальные запросы требуют авторизации
            )
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // говорим Spring Security не хранить сессии на сервере: каждый запрос должен содержать всю информацию
            );

        // Добавляем фильтр JWT перед стандартным UsernamePasswordAuthenticationFilter сначала проходит проверка JWT, потом (если нужно) стандартная аутентификация по логин/паролю.
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
