package com.pethost.pethost.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Define o header "WWW-Authenticate" para JWT com informações sobre o realm
        response.setHeader("WWW-Authenticate", "Bearer realm='/auth/login'");

        // Envia a resposta com o status 401 e uma mensagem explicativa no corpo
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Erro: Acesso negado. Token JWT ausente ou inválido.");
    }
}