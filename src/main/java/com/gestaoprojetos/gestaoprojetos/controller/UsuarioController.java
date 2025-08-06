package com.gestaoprojetos.gestaoprojetos.controller;

import com.gestaoprojetos.gestaoprojetos.dto.request.UsuarioRequest;
import com.gestaoprojetos.gestaoprojetos.model.Usuario;
import com.gestaoprojetos.gestaoprojetos.service.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    // GET / - obter todos
    @GetMapping
    public List<Usuario> findAll() {
        return usuarioService.getAllUsuarios();
    }


    // GET /id - obter um
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable("id") Long index) {
        Optional<Usuario> resultado = usuarioService.getById(index);
        return ResponseEntity.of(resultado);
    }
    // POST - criar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario add(@RequestBody @Valid Usuario usuario) {
        return usuarioService.add(usuario);
    }
    // PUT /id - modificar
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long index, @RequestBody UsuarioRequest dto) {
        usuarioService.update(index, dto);
    }
    // DELETE /id - apagar
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long index) {
        usuarioService.delete(index);
    }
}
