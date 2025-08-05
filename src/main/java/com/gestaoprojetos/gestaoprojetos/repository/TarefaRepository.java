package com.gestaoprojetos.gestaoprojetos.repository;

import com.gestaoprojetos.gestaoprojetos.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {}
