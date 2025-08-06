package com.gestaoprojetos.gestaoprojetos.service;

import com.gestaoprojetos.gestaoprojetos.dto.mapper.ProjetoMapper;
import com.gestaoprojetos.gestaoprojetos.dto.request.ProjetoRequest;
import com.gestaoprojetos.gestaoprojetos.model.Projeto;
import com.gestaoprojetos.gestaoprojetos.model.Usuario;
import com.gestaoprojetos.gestaoprojetos.repository.ProjetoRepository;
import com.gestaoprojetos.gestaoprojetos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProjetoMapper mapper;

    // GET
    public List<Projeto> getAllProjetos(){
        return projetoRepository.findAll();
    }

    // GET - {id}
    public Optional<Projeto> getById(Long id){
        return projetoRepository.findById(id);
    }

    // DELETE
    public void delete(Long id){
        projetoRepository.deleteById(id);
    }

    // POST
    public Projeto add(ProjetoRequest projetoRequest) {
        Projeto projeto = mapper.toEntity(projetoRequest);
        return projetoRepository.save(projeto);
    }

    // PUT
    public Projeto update(Long id, ProjetoRequest dto) {
        Projeto projeto = projetoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        
        mapper.updateEntity(dto, projeto);
        return projetoRepository.save(projeto);
    }

    // POST - Associar usuário ao projeto
    public Projeto associarUsuario(Long projetoId, Long usuarioId) {
        Projeto projeto = projetoRepository.findById(projetoId)
            .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Verifica se o usuário já está associado ao projeto
        if (projeto.getUsuarios().contains(usuario)) {
            throw new IllegalArgumentException("Usuário já está associado a este projeto");
        }

        // Sincronização bidirecional
        projeto.getUsuarios().add(usuario);
        usuario.getProjetos().add(projeto);
        
        // Salva ambos para garantir a sincronização
        usuarioRepository.save(usuario);
        return projetoRepository.save(projeto);
    }

    // DELETE - Remover usuário do projeto
    public Projeto desassociarUsuario(Long projetoId, Long usuarioId) {
        Projeto projeto = projetoRepository.findById(projetoId)
            .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Verifica se o usuário está associado ao projeto
        if (!projeto.getUsuarios().contains(usuario)) {
            throw new IllegalArgumentException("Usuário não está associado a este projeto");
        }

        // Sincronização bidirecional na remoção
        projeto.getUsuarios().remove(usuario);
        usuario.getProjetos().remove(projeto);
        
        // Salva ambos para garantir a sincronização
        usuarioRepository.save(usuario);
        return projetoRepository.save(projeto);
    }
}
