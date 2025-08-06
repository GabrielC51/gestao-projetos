package com.gestaoprojetos.gestaoprojetos.controller;

import com.gestaoprojetos.gestaoprojetos.dto.request.ProjetoRequest;
import com.gestaoprojetos.gestaoprojetos.dto.request.ProjetoUsuarioRequest;
import com.gestaoprojetos.gestaoprojetos.model.Projeto;
import com.gestaoprojetos.gestaoprojetos.service.ProjetoService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/projetos")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Projetos", description = "Endpoints para gerenciamento de projetos")
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

    // POST /{projetoId}/usuarios - associar usuário ao projeto
    @PostMapping("/{projetoId}/usuarios")
    @Operation(summary = "Associar usuário ao projeto")
    public ResponseEntity<Projeto> associarUsuario(
            @PathVariable("projetoId") Long projetoId,
            @RequestBody @Valid ProjetoUsuarioRequest request) {
        Projeto projeto = projetoService.associarUsuario(projetoId, request.getUsuarioId());
        return ResponseEntity.ok(projeto);
    }

    // DELETE /{projetoId}/usuarios/{usuarioId} - remover usuário do projeto
    @DeleteMapping("/{projetoId}/usuarios/{usuarioId}")
    @Operation(summary = "Remover usuário do projeto")
    public ResponseEntity<Projeto> desassociarUsuario(
            @PathVariable("projetoId") Long projetoId,
            @PathVariable("usuarioId") Long usuarioId) {
        Projeto projeto = projetoService.desassociarUsuario(projetoId, usuarioId);
        return ResponseEntity.ok(projeto);
    }
}
