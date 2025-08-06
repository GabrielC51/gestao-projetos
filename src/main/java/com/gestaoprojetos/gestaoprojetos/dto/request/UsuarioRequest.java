package com.gestaoprojetos.gestaoprojetos.dto.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
public class UsuarioRequest {

    @NotNull
    @Size(min = 3)
    private String nome;

    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min = 3)
    private String cargo;
}
