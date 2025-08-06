package com.gestaoprojetos.gestaoprojetos.dto.mapper;

import com.gestaoprojetos.gestaoprojetos.dto.request.ProjetoRequest;
import com.gestaoprojetos.gestaoprojetos.model.Projeto;
import org.springframework.stereotype.Component;

@Component
public class ProjetoMapper {

    public Projeto toEntity(ProjetoRequest dto) {
        Projeto projeto = new Projeto();
        projeto.setTitulo(dto.getTitulo());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataEntrega(dto.getDataEntrega());
        return projeto;
    }

    public void updateEntity(ProjetoRequest dto, Projeto projeto) {
        projeto.setTitulo(dto.getTitulo());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataEntrega(dto.getDataEntrega());
    }
}
