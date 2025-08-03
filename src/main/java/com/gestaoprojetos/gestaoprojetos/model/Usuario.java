package com.gestaoprojetos.gestaoprojetos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

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
    private String Email;

    @NotNull
    @Size(min = 3)
    @Column
    private String cargo;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Tarefa> tarefas;

    @ManyToMany
    private List<Projeto> projetos;
}