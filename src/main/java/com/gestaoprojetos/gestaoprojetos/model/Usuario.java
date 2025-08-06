package com.gestaoprojetos.gestaoprojetos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    @NotNull
    @Size(min = 3)
    @Column
    private String nome;

    @Email
    @Column(unique = true)
    @NotNull
    private String email;

    @NotNull
    @Size(min = 6)
    @Column
    private String senha;

    @NotNull
    @Size(min = 3)
    @Column
    private String cargo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tarefa> tarefas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "usuario_projeto",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "projeto_id")
    )
    
    private List<Projeto> projetos = new ArrayList<>();

    public Usuario(String nome, String email, String senha, String cargo, List<Tarefa> tarefas, List<Projeto> projetos) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.tarefas = tarefas;
        this.projetos = projetos;
    }
}