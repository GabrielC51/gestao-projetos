package com.gestaoprojetos.gestaoprojetos.controller;

import com.gestaoprojetos.gestaoprojetos.dto.request.UsuarioRequest;
import com.gestaoprojetos.gestaoprojetos.model.Usuario;
import com.gestaoprojetos.gestaoprojetos.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    // GET / - obter todos
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> findAll() {
        return usuarioService.getAllUsuarios();
    }


    // GET /id - obter um
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Usuario> findById(@PathVariable("id") Long index) {
      Optional<Usuario> resultado = usuarioService.getById(index);
      return ResponseEntity.of(resultado);
    }
    
    // POST - criar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario add(@RequestBody @Valid UsuarioRequest usuarioRequest) {
      return usuarioService.add(usuarioRequest);
    }
    
    // PUT /id - modificar
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long index, @RequestBody UsuarioRequest dto) {
      usuarioService.update(index, dto);
    }
    
    // DELETE /id - apagar
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long index) {
        usuarioService.delete(index);
    }
}
