package com.gestaoprojetos.gestaoprojetos.service;

import com.gestaoprojetos.gestaoprojetos.dto.mapper.UsuarioMapper;
import com.gestaoprojetos.gestaoprojetos.dto.request.UsuarioRequest;
import com.gestaoprojetos.gestaoprojetos.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestaoprojetos.gestaoprojetos.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper mapper;

    // GET
    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    // GET - {id}
    public Optional<Usuario> getById(Long id){
        return usuarioRepository.findById(id);
    }

    // DELETE
    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }

    // POST
    public Usuario add(UsuarioRequest usuarioRequest) {
        if (usuarioRepository.existsByEmail(usuarioRequest.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        Usuario usuario = mapper.toEntity(usuarioRequest);
        return usuarioRepository.save(usuario);
    }

    // PUT
    public Usuario update(Long id, UsuarioRequest dto) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));
        
        mapper.updateEntity(dto, usuario);
        return usuarioRepository.save(usuario);
    }

}
