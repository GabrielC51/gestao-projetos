package com.gestaoprojetos.gestaoprojetos.service;

import com.gestaoprojetos.gestaoprojetos.dto.mapper.ProjetoMapper;
import com.gestaoprojetos.gestaoprojetos.dto.request.ProjetoRequest;
import com.gestaoprojetos.gestaoprojetos.model.Projeto;
import com.gestaoprojetos.gestaoprojetos.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

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
}
