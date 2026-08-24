package com.lucas.userservice.service;

import com.lucas.userservice.dto.request.LoginRequest;
import com.lucas.userservice.dto.request.UserRequest;
import com.lucas.userservice.dto.response.UserResponse;
import com.lucas.userservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService; // Seu serviço existente para registro de usuários

    public String login(LoginRequest request) {
        // 1. O AuthenticationManager valida as credenciais contra o UserDetailsService e PasswordEncoder
        var authToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(authToken);

        // 2. Extrai o usuário autenticado e gera o token JWT
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        assert userDetails != null;
        return jwtService.generateToken(userDetails.getUsername());
    }

    public UserResponse register(UserRequest request) {
        return userService.create(request);
    }
}
