package com.gestaoprojetos.gestaoprojetos.controller;

import com.gestaoprojetos.gestaoprojetos.dto.request.TarefaRequest;
import com.gestaoprojetos.gestaoprojetos.model.Tarefa;
import com.gestaoprojetos.gestaoprojetos.service.TarefaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Tarefas", description = "Endpoints para gerenciamento de tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    // GET / - obter todos
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Tarefa> findAll() {
        return tarefaService.getAllTarefas();
    }

    // GET /id - obter um
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Tarefa> findById(@PathVariable("id") Long id) {
        Optional<Tarefa> resultado = tarefaService.getById(id);
        return ResponseEntity.of(resultado);
    }
    
    // POST - criar
    @PostMapping
    public ResponseEntity<Tarefa> add(@RequestBody @Valid TarefaRequest tarefaRequest) {
        try {
            Tarefa tarefa = tarefaService.add(tarefaRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Erro ao criar tarefa: " + e.getMessage());
        }
    }
    
    // PUT /id - modificar
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tarefa update(@PathVariable("id") Long id, @RequestBody @Valid TarefaRequest dto) {
        return tarefaService.update(id, dto);
    }
    
    // DELETE /id - apagar
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        tarefaService.delete(id);
    }
}
