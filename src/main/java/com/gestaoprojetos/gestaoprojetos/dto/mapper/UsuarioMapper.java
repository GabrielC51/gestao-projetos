package com.gestaoprojetos.gestaoprojetos.dto.mapper;

import com.gestaoprojetos.gestaoprojetos.dto.request.UsuarioRequest;
import com.gestaoprojetos.gestaoprojetos.model.Usuario;

import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    
    public Usuario toEntity(UsuarioRequest dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setCargo(dto.getCargo());
        return usuario;
    }
    
    public void updateEntity(UsuarioRequest dto, Usuario usuario) {
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setCargo(dto.getCargo());
    }
}
