package com.gestaoprojetos.gestaoprojetos.service;

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

    //get
    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    //get/{id}
    public Optional<Usuario> getById(Long id){
        return usuarioRepository.findById(id);
    }

    //delete
    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }

    //post
    public Usuario add(Usuario usuario){
        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new IllegalArgumentException("Email já cadastrado");
        }
        return usuarioRepository.save(usuario);
    }

    //put
    public Usuario update(Long id, UsuarioRequest dto){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isEmpty()){
            throw new IllegalArgumentException("Usuario não encontrado");
        }

        Usuario usuario = usuarioOptional.get();
        usuario.setUsuarioId(id);
        usuario.setNome(dto.getNome());
        usuario.setCargo(dto.getCargo());
        usuario.setEmail(dto.getEmail());

        return usuarioRepository.save(usuario);
    }

}
