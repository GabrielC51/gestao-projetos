package com.gestaoprojetos.gestaoprojetos.repository;

import com.gestaoprojetos.gestaoprojetos.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
