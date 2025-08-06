package com.gestaoprojetos.gestaoprojetos.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjetoUsuarioRequest {

    @NotNull(message = "ID do usuário é obrigatório")
    private Long usuarioId;
}
