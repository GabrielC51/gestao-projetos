package com.gestaoprojetos.gestaoprojetos.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TarefaRequest {

    @NotNull
    @Size(min = 3)
    private String titulo;

    @NotNull
    @Size(min = 5, message = "Descrição deve ter no mínimo 5 caracteres")
    private String descricao;

    @NotNull
    private LocalDateTime dataEntrega;

    @NotNull
    private Long usuarioId;

    @NotNull
    private Long projetoId;
}
