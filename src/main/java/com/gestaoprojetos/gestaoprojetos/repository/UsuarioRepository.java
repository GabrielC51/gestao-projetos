package com.gestaoprojetos.gestaoprojetos.repository;

import com.gestaoprojetos.gestaoprojetos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
