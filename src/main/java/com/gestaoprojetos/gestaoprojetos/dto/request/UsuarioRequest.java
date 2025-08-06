package com.gestaoprojetos.gestaoprojetos.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioRequest {

    @NotNull
    @Size(min = 3)
    private String nome;

    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotNull
    @Size(min = 3)
    private String cargo;
}
