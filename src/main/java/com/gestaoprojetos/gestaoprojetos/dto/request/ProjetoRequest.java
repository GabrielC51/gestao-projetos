package com.gestaoprojetos.gestaoprojetos.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjetoRequest {

    @NotNull
    @Size(min = 3)
    private String titulo;

    @NotNull
    @Size(min = 10)
    private String descricao;

    @NotNull
    private Date dataEntrega;
}
