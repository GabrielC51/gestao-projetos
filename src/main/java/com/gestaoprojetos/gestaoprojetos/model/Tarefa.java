package com.gestaoprojetos.gestaoprojetos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tarefaId;

    @NotNull
    private String titulo;

    @NotNull
    private String descricao;

    @NotNull
    private LocalDateTime dataEntrega;

    @ManyToOne
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JsonIgnore
    private Projeto projeto;
}
