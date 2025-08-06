package com.gestaoprojetos.gestaoprojetos.service;

import com.gestaoprojetos.gestaoprojetos.dto.mapper.TarefaMapper;
import com.gestaoprojetos.gestaoprojetos.dto.request.TarefaRequest;
import com.gestaoprojetos.gestaoprojetos.model.Projeto;
import com.gestaoprojetos.gestaoprojetos.model.Tarefa;
import com.gestaoprojetos.gestaoprojetos.model.Usuario;
import com.gestaoprojetos.gestaoprojetos.repository.ProjetoRepository;
import com.gestaoprojetos.gestaoprojetos.repository.TarefaRepository;
import com.gestaoprojetos.gestaoprojetos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private TarefaMapper mapper;

    public List<Tarefa> getAllTarefas(){
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> getById(Long id){
        return tarefaRepository.findById(id);
    }

    public void delete(Long id){
        tarefaRepository.deleteById(id);
    }

    public Tarefa add(TarefaRequest tarefaRequest) {
        Usuario usuario = usuarioRepository.findById(tarefaRequest.getUsuarioId())
            .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));
        
        Projeto projeto = projetoRepository.findById(tarefaRequest.getProjetoId())
            .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        
        Tarefa tarefa = mapper.toEntity(tarefaRequest, usuario, projeto);
        return tarefaRepository.save(tarefa);
    }

    public Tarefa update(Long id, TarefaRequest dto) {
        Tarefa tarefa = tarefaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
            .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));
        
        Projeto projeto = projetoRepository.findById(dto.getProjetoId())
            .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        
        mapper.updateEntity(dto, tarefa, usuario, projeto);
        return tarefaRepository.save(tarefa);
    }
}
