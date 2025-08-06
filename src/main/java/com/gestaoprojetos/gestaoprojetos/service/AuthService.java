package com.gestaoprojetos.gestaoprojetos.service;

import com.gestaoprojetos.gestaoprojetos.dto.request.LoginRequest;
import com.gestaoprojetos.gestaoprojetos.dto.response.LoginResponse;
import com.gestaoprojetos.gestaoprojetos.model.Usuario;
import com.gestaoprojetos.gestaoprojetos.repository.UsuarioRepository;
import com.gestaoprojetos.gestaoprojetos.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) {
        // Buscar usuário por email
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas"));

        // Verificar senha
        if (!passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha())) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        // Gerar token JWT
        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getCargo());

        // Retornar resposta com token e dados do usuário
        return new LoginResponse(token, usuario.getEmail(), usuario.getNome(), usuario.getCargo());
    }
}
