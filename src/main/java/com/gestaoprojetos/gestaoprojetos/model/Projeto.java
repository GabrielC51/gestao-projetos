package com.gestaoprojetos.gestaoprojetos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projetoId;

    @NotNull
    private String titulo;

    @NotNull
    private String descricao;

    @NotNull
    private Date dataEntrega;

    @ManyToMany(mappedBy = "projetos", fetch = FetchType.EAGER)
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "projeto", fetch = FetchType.EAGER)
    private List<Tarefa> tarefas;
}
