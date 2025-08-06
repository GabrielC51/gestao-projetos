package com.gestaoprojetos.gestaoprojetos.repository;

import com.gestaoprojetos.gestaoprojetos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByEmail(String email);
    public boolean existsByEmail(String email);
}
