package com.gestaoprojetos.gestaoprojetos.controller;

import com.gestaoprojetos.gestaoprojetos.dto.request.ProjetoRequest;
import com.gestaoprojetos.gestaoprojetos.model.Projeto;
import com.gestaoprojetos.gestaoprojetos.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    // GET / - obter todos
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Projeto> findAll() {
        return projetoService.getAllProjetos();
    }

    // GET /id - obter um
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Projeto> findById(@PathVariable("id") Long id) {
        Optional<Projeto> resultado = projetoService.getById(id);
        return ResponseEntity.of(resultado);
    }
    
    // POST - criar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Projeto add(@RequestBody @Valid ProjetoRequest projetoRequest) {
        return projetoService.add(projetoRequest);
    }
    
    // PUT /id - modificar
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Projeto update(@PathVariable("id") Long id, @RequestBody @Valid ProjetoRequest dto) {
        return projetoService.update(id, dto);
    }
    
    // DELETE /id - apagar
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        projetoService.delete(id);
    }
}
