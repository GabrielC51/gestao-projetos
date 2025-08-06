package com.gestaoprojetos.gestaoprojetos.dto.mapper;

import com.gestaoprojetos.gestaoprojetos.dto.request.UsuarioRequest;
import com.gestaoprojetos.gestaoprojetos.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Usuario toEntity(UsuarioRequest dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setCargo(dto.getCargo());
        return usuario;
    }
    
    public void updateEntity(UsuarioRequest dto, Usuario usuario) {
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setCargo(dto.getCargo());
    }
}
