package com.gestaoprojetos.gestaoprojetos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse {
    private String token;
    private String tipo;
    private String email;
    private String nome;
    private String cargo;

    public LoginResponse(String token, String email, String nome, String cargo) {
        this.token = token;
        this.tipo = "Bearer";
        this.email = email;
        this.nome = nome;
        this.cargo = cargo;
    }
}
