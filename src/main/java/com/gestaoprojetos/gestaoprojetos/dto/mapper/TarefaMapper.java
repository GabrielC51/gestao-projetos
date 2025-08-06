package com.gestaoprojetos.gestaoprojetos.dto.mapper;

import com.gestaoprojetos.gestaoprojetos.dto.request.TarefaRequest;
import com.gestaoprojetos.gestaoprojetos.model.Projeto;
import com.gestaoprojetos.gestaoprojetos.model.Tarefa;
import com.gestaoprojetos.gestaoprojetos.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class TarefaMapper {

    public Tarefa toEntity(TarefaRequest dto, Usuario usuario, Projeto projeto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setDataEntrega(dto.getDataEntrega());
        tarefa.setUsuario(usuario);
        tarefa.setProjeto(projeto);
        
        return tarefa;
    }

    public void updateEntity(TarefaRequest dto, Tarefa tarefa, Usuario usuario, Projeto projeto) {
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setDataEntrega(dto.getDataEntrega());
        tarefa.setUsuario(usuario);
        tarefa.setProjeto(projeto);
    }

}
