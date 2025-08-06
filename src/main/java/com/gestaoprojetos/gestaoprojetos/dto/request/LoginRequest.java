package com.gestaoprojetos.gestaoprojetos.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String senha;
}
