package com.gestaoprojetos.gestaoprojetos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private LocalDateTime dataEntrega;
 
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tarefa> tarefas = new ArrayList<>();
    
    @ManyToMany(mappedBy = "projetos", fetch = FetchType.EAGER)
    @JsonBackReference("usuario-projetos")
    private List<Usuario> usuarios = new ArrayList<>();
}
